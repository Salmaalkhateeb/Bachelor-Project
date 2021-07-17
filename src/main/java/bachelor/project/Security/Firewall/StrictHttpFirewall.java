package bachelor.project.Security.Firewall;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.util.Assert;

public class StrictHttpFirewall implements HttpFirewall {

    private static final Set<String> ALLOW_ANY_HTTP_METHOD = Collections.emptySet();

    private static final String ENCODED_PERCENT = "%25";

    private static final String PERCENT = "%";

    private static final List<String> FORBIDDEN_ENCODED_PERIOD = Collections
            .unmodifiableList(Arrays.asList("%2e", "%2E"));

    private static final List<String> FORBIDDEN_SEMICOLON = Collections
            .unmodifiableList(Arrays.asList(";", "%3b", "%3B"));

    private static final List<String> FORBIDDEN_FORWARDSLASH = Collections
            .unmodifiableList(Arrays.asList("%2f", "%2F"));

    private static final List<String> FORBIDDEN_DOUBLE_FORWARDSLASH = Collections
            .unmodifiableList(Arrays.asList("//", "%2f%2f", "%2f%2F", "%2F%2f", "%2F%2F"));

    private static final List<String> FORBIDDEN_BACKSLASH = Collections
            .unmodifiableList(Arrays.asList("\\", "%5c", "%5C"));

    private static final List<String> FORBIDDEN_NULL = Collections.unmodifiableList(Arrays.asList("\0", "%00"));

    private Set<String> encodedUrlBlocklist = new HashSet<>();

    private Set<String> decodedUrlBlocklist = new HashSet<>();

    private Set<String> allowedHttpMethods = createDefaultAllowedHttpMethods();

    private Predicate<String> allowedHostnames = (hostname) -> true;

    private static final Pattern ASSIGNED_AND_NOT_ISO_CONTROL_PATTERN = Pattern
            .compile("[\\p{IsAssigned}&&[^\\p{IsControl}]]*");

    private static final Predicate<String> ASSIGNED_AND_NOT_ISO_CONTROL_PREDICATE = (
            s) -> ASSIGNED_AND_NOT_ISO_CONTROL_PATTERN.matcher(s).matches();

    private Predicate<String> allowedHeaderNames = ASSIGNED_AND_NOT_ISO_CONTROL_PREDICATE;

    private Predicate<String> allowedHeaderValues = ASSIGNED_AND_NOT_ISO_CONTROL_PREDICATE;

    private Predicate<String> allowedParameterNames = ASSIGNED_AND_NOT_ISO_CONTROL_PREDICATE;

    private Predicate<String> allowedParameterValues = (value) -> true;

    public StrictHttpFirewall() {
        urlBlocklistsAddAll(FORBIDDEN_SEMICOLON);
        urlBlocklistsAddAll(FORBIDDEN_FORWARDSLASH);
        urlBlocklistsAddAll(FORBIDDEN_DOUBLE_FORWARDSLASH);
        urlBlocklistsAddAll(FORBIDDEN_BACKSLASH);
        urlBlocklistsAddAll(FORBIDDEN_NULL);

        this.encodedUrlBlocklist.add(ENCODED_PERCENT);
        this.encodedUrlBlocklist.addAll(FORBIDDEN_ENCODED_PERIOD);
        this.decodedUrlBlocklist.add(PERCENT);
    }

    public void setUnsafeAllowAnyHttpMethod(boolean unsafeAllowAnyHttpMethod) {
        this.allowedHttpMethods = unsafeAllowAnyHttpMethod ? ALLOW_ANY_HTTP_METHOD : createDefaultAllowedHttpMethods();
    }

    public void setAllowedHttpMethods(Collection<String> allowedHttpMethods) {
        Assert.notNull(allowedHttpMethods, "allowedHttpMethods cannot be null");
        this.allowedHttpMethods = (allowedHttpMethods != ALLOW_ANY_HTTP_METHOD) ? new HashSet<>(allowedHttpMethods)
                : ALLOW_ANY_HTTP_METHOD;
    }

    public void setAllowSemicolon(boolean allowSemicolon) {
        if (allowSemicolon) {
            urlBlocklistsRemoveAll(FORBIDDEN_SEMICOLON);
        }
        else {
            urlBlocklistsAddAll(FORBIDDEN_SEMICOLON);
        }
    }


    public void setAllowUrlEncodedSlash(boolean allowUrlEncodedSlash) {
        if (allowUrlEncodedSlash) {
            urlBlocklistsRemoveAll(FORBIDDEN_FORWARDSLASH);
        }
        else {
            urlBlocklistsAddAll(FORBIDDEN_FORWARDSLASH);
        }
    }


    public void setAllowUrlEncodedDoubleSlash(boolean allowUrlEncodedDoubleSlash) {
        if (allowUrlEncodedDoubleSlash) {
            urlBlocklistsRemoveAll(FORBIDDEN_DOUBLE_FORWARDSLASH);
        }
        else {
            urlBlocklistsAddAll(FORBIDDEN_DOUBLE_FORWARDSLASH);
        }
    }


    public void setAllowUrlEncodedPeriod(boolean allowUrlEncodedPeriod) {
        if (allowUrlEncodedPeriod) {
            this.encodedUrlBlocklist.removeAll(FORBIDDEN_ENCODED_PERIOD);
        }
        else {
            this.encodedUrlBlocklist.addAll(FORBIDDEN_ENCODED_PERIOD);
        }
    }

    public void setAllowBackSlash(boolean allowBackSlash) {
        if (allowBackSlash) {
            urlBlocklistsRemoveAll(FORBIDDEN_BACKSLASH);
        }
        else {
            urlBlocklistsAddAll(FORBIDDEN_BACKSLASH);
        }
    }

    public void setAllowNull(boolean allowNull) {
        if (allowNull) {
            urlBlocklistsRemoveAll(FORBIDDEN_NULL);
        }
        else {
            urlBlocklistsAddAll(FORBIDDEN_NULL);
        }
    }

    public void setAllowUrlEncodedPercent(boolean allowUrlEncodedPercent) {
        if (allowUrlEncodedPercent) {
            this.encodedUrlBlocklist.remove(ENCODED_PERCENT);
            this.decodedUrlBlocklist.remove(PERCENT);
        }
        else {
            this.encodedUrlBlocklist.add(ENCODED_PERCENT);
            this.decodedUrlBlocklist.add(PERCENT);
        }
    }


    public void setAllowedHeaderNames(Predicate<String> allowedHeaderNames) {
        Assert.notNull(allowedHeaderNames, "allowedHeaderNames cannot be null");
        this.allowedHeaderNames = allowedHeaderNames;
    }

    public void setAllowedHeaderValues(Predicate<String> allowedHeaderValues) {
        Assert.notNull(allowedHeaderValues, "allowedHeaderValues cannot be null");
        this.allowedHeaderValues = allowedHeaderValues;
    }

    public void setAllowedParameterNames(Predicate<String> allowedParameterNames) {
        Assert.notNull(allowedParameterNames, "allowedParameterNames cannot be null");
        this.allowedParameterNames = allowedParameterNames;
    }


    public void setAllowedParameterValues(Predicate<String> allowedParameterValues) {
        Assert.notNull(allowedParameterValues, "allowedParameterValues cannot be null");
        this.allowedParameterValues = allowedParameterValues;
    }

    public void setAllowedHostnames(Predicate<String> allowedHostnames) {
        Assert.notNull(allowedHostnames, "allowedHostnames cannot be null");
        this.allowedHostnames = allowedHostnames;
    }

    private void urlBlocklistsAddAll(Collection<String> values) {
        this.encodedUrlBlocklist.addAll(values);
        this.decodedUrlBlocklist.addAll(values);
    }

    private void urlBlocklistsRemoveAll(Collection<String> values) {
        this.encodedUrlBlocklist.removeAll(values);
        this.decodedUrlBlocklist.removeAll(values);
    }

    @Override
    public FirewalledRequest getFirewalledRequest(HttpServletRequest request) throws RequestRejectedException {
        rejectForbiddenHttpMethod(request);
        rejectedBlocklistedUrls(request);
        rejectedUntrustedHosts(request);
        if (!isNormalized(request)) {
            throw new RequestRejectedException("The request was rejected because the URL was not normalized.");
        }
        String requestUri = request.getRequestURI();
        if (!containsOnlyPrintableAsciiCharacters(requestUri)) {
            throw new RequestRejectedException(
                    "The requestURI was rejected because it can only contain printable ASCII characters.");
        }
        return new StrictFirewalledRequest(request);
    }

    private void rejectForbiddenHttpMethod(HttpServletRequest request) {
        if (this.allowedHttpMethods == ALLOW_ANY_HTTP_METHOD) {
            return;
        }
        if (!this.allowedHttpMethods.contains(request.getMethod())) {
            throw new RequestRejectedException(
                    "The request was rejected because the HTTP method \"" + request.getMethod()
                            + "\" was not included within the list of allowed HTTP methods " + this.allowedHttpMethods);
        }
    }

    private void rejectedBlocklistedUrls(HttpServletRequest request) {
        for (String forbidden : this.encodedUrlBlocklist) {
            if (encodedUrlContains(request, forbidden)) {
                throw new RequestRejectedException(
                        "The request was rejected because the URL contained a potentially malicious String \""
                                + forbidden + "\"");
            }
        }
        for (String forbidden : this.decodedUrlBlocklist) {
            if (decodedUrlContains(request, forbidden)) {
                throw new RequestRejectedException(
                        "The request was rejected because the URL contained a potentially malicious String \""
                                + forbidden + "\"");
            }
        }
    }

    private void rejectedUntrustedHosts(HttpServletRequest request) {
        String serverName = request.getServerName();
        if (serverName != null && !this.allowedHostnames.test(serverName)) {
            throw new RequestRejectedException(
                    "The request was rejected because the domain " + serverName + " is untrusted.");
        }
    }

    @Override
    public HttpServletResponse getFirewalledResponse(HttpServletResponse response) {
        return new FirewalledResponse(response);
    }

    private static Set<String> createDefaultAllowedHttpMethods() {
        Set<String> result = new HashSet<>();
        result.add(HttpMethod.DELETE.name());
        result.add(HttpMethod.GET.name());
        result.add(HttpMethod.HEAD.name());
        result.add(HttpMethod.OPTIONS.name());
        result.add(HttpMethod.PATCH.name());
        result.add(HttpMethod.POST.name());
        result.add(HttpMethod.PUT.name());
        return result;
    }

    private static boolean isNormalized(HttpServletRequest request) {
        if (!isNormalized(request.getRequestURI())) {
            return false;
        }
        if (!isNormalized(request.getContextPath())) {
            return false;
        }
        if (!isNormalized(request.getServletPath())) {
            return false;
        }
        if (!isNormalized(request.getPathInfo())) {
            return false;
        }
        return true;
    }

    private static boolean encodedUrlContains(HttpServletRequest request, String value) {
        if (valueContains(request.getContextPath(), value)) {
            return true;
        }
        return valueContains(request.getRequestURI(), value);
    }

    private static boolean decodedUrlContains(HttpServletRequest request, String value) {
        if (valueContains(request.getServletPath(), value)) {
            return true;
        }
        if (valueContains(request.getPathInfo(), value)) {
            return true;
        }
        return false;
    }

    private static boolean containsOnlyPrintableAsciiCharacters(String uri) {
        int length = uri.length();
        for (int i = 0; i < length; i++) {
            char ch = uri.charAt(i);
            if (ch < '\u0020' || ch > '\u007e') {
                return false;
            }
        }
        return true;
    }

    private static boolean valueContains(String value, String contains) {
        return value != null && value.contains(contains);
    }

    /**
     * Checks whether a path is normalized (doesn't contain path traversal sequences like
     * "./", "/../" or "/.")
     * @param path the path to test
     * @return true if the path doesn't contain any path-traversal character sequences.
     */
    private static boolean isNormalized(String path) {
        if (path == null) {
            return true;
        }
        for (int i = path.length(); i > 0;) {
            int slashIndex = path.lastIndexOf('/', i - 1);
            int gap = i - slashIndex;
            if (gap == 2 && path.charAt(slashIndex + 1) == '.') {
                return false; // ".", "/./" or "/."
            }
            if (gap == 3 && path.charAt(slashIndex + 1) == '.' && path.charAt(slashIndex + 2) == '.') {
                return false;
            }
            i = slashIndex;
        }
        return true;
    }

    /**
     * Provides the existing encoded url blocklist which can add/remove entries from
     * @return the existing encoded url blocklist, never null
     */
    public Set<String> getEncodedUrlBlocklist() {
        return this.encodedUrlBlocklist;
    }

    /**
     * Provides the existing decoded url blocklist which can add/remove entries from
     * @return the existing decoded url blocklist, never null
     */
    public Set<String> getDecodedUrlBlocklist() {
        return this.decodedUrlBlocklist;
    }

    /**
     * Provides the existing encoded url blocklist which can add/remove entries from
     * @return the existing encoded url blocklist, never null
     * @deprecated Use {@link #getEncodedUrlBlocklist()} instead
     */
    @Deprecated
    public Set<String> getEncodedUrlBlacklist() {
        return getEncodedUrlBlocklist();
    }

    /**
     * Provides the existing decoded url blocklist which can add/remove entries from
     * @return the existing decoded url blocklist, never null
     *
     */
    public Set<String> getDecodedUrlBlacklist() {
        return getDecodedUrlBlocklist();
    }

    /**
     * Strict {@link FirewalledRequest}.
     */
    private class StrictFirewalledRequest extends FirewalledRequest {

        StrictFirewalledRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public long getDateHeader(String name) {
            if (name != null) {
                validateAllowedHeaderName(name);
            }
            return super.getDateHeader(name);
        }

        @Override
        public int getIntHeader(String name) {
            if (name != null) {
                validateAllowedHeaderName(name);
            }
            return super.getIntHeader(name);
        }

        @Override
        public String getHeader(String name) {
            if (name != null) {
                validateAllowedHeaderName(name);
            }
            String value = super.getHeader(name);
            if (value != null) {
                validateAllowedHeaderValue(value);
            }
            return value;
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (name != null) {
                validateAllowedHeaderName(name);
            }
            Enumeration<String> headers = super.getHeaders(name);
            return new Enumeration<String>() {

                @Override
                public boolean hasMoreElements() {
                    return headers.hasMoreElements();
                }

                @Override
                public String nextElement() {
                    String value = headers.nextElement();
                    validateAllowedHeaderValue(value);
                    return value;
                }

            };
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            Enumeration<String> names = super.getHeaderNames();
            return new Enumeration<String>() {

                @Override
                public boolean hasMoreElements() {
                    return names.hasMoreElements();
                }

                @Override
                public String nextElement() {
                    String headerNames = names.nextElement();
                    validateAllowedHeaderName(headerNames);
                    return headerNames;
                }

            };
        }

        @Override
        public String getParameter(String name) {
            if (name != null) {
                validateAllowedParameterName(name);
            }
            String value = super.getParameter(name);
            if (value != null) {
                validateAllowedParameterValue(value);
            }
            return value;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            Map<String, String[]> parameterMap = super.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String name = entry.getKey();
                String[] values = entry.getValue();
                validateAllowedParameterName(name);
                for (String value : values) {
                    validateAllowedParameterValue(value);
                }
            }
            return parameterMap;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            Enumeration<String> paramaterNames = super.getParameterNames();
            return new Enumeration<String>() {

                @Override
                public boolean hasMoreElements() {
                    return paramaterNames.hasMoreElements();
                }

                @Override
                public String nextElement() {
                    String name = paramaterNames.nextElement();
                    validateAllowedParameterName(name);
                    return name;
                }

            };
        }

        @Override
        public String[] getParameterValues(String name) {
            if (name != null) {
                validateAllowedParameterName(name);
            }
            String[] values = super.getParameterValues(name);
            if (values != null) {
                for (String value : values) {
                    validateAllowedParameterValue(value);
                }
            }
            return values;
        }

        private void validateAllowedHeaderName(String headerNames) {
            if (!StrictHttpFirewall.this.allowedHeaderNames.test(headerNames)) {
                throw new RequestRejectedException(
                        "The request was rejected because the header name \"" + headerNames + "\" is not allowed.");
            }
        }

        private void validateAllowedHeaderValue(String value) {
            if (!StrictHttpFirewall.this.allowedHeaderValues.test(value)) {
                throw new RequestRejectedException(
                        "The request was rejected because the header value \"" + value + "\" is not allowed.");
            }
        }

        private void validateAllowedParameterName(String name) {
            if (!StrictHttpFirewall.this.allowedParameterNames.test(name)) {
                throw new RequestRejectedException(
                        "The request was rejected because the parameter name \"" + name + "\" is not allowed.");
            }
        }

        private void validateAllowedParameterValue(String value) {
            if (!StrictHttpFirewall.this.allowedParameterValues.test(value)) {
                throw new RequestRejectedException(
                        "The request was rejected because the parameter value \"" + value + "\" is not allowed.");
            }
        }

        @Override
        public void reset() {
        }

    };

}