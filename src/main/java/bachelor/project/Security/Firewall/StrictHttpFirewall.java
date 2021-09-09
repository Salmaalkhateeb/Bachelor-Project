//package bachelor.project.Security.Firewall;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.web.firewall.FirewalledRequest;
//import org.springframework.security.web.firewall.HttpFirewall;
//import org.springframework.security.web.firewall.RequestRejectedException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashSet;
//import java.util.Set;
//
//public class StrictHttpFirewall implements HttpFirewall {
//    private Set<String> allowedHttpMethods = createDefaultAllowedHttpMethods();
//    private static Set<String> createDefaultAllowedHttpMethods() {
//        Set<String> result = new HashSet();
//        result.add(HttpMethod.GET.name());
//        result.add(HttpMethod.POST.name());
//
//        return result;
//    }
//
//    public void rejectForbiddenHttpMethod(HttpServletRequest request) {
//
//        if (!this.allowedHttpMethods.contains(request.getMethod())) {
//            throw new RequestRejectedException("The request was rejected because the HTTP method \"" +
//                    request.getMethod() +
//                    "\" was not included within the whitelist " +
//                    this.allowedHttpMethods);
//        }
//    }
//
//    @Override
//    public FirewalledRequest getFirewalledRequest(HttpServletRequest httpServletRequest) throws RequestRejectedException {
//        return null;
//    }
//
//    @Override
//    public HttpServletResponse getFirewalledResponse(HttpServletResponse httpServletResponse) {
//        return null;
//    }
//}