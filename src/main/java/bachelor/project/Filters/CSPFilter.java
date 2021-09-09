//package bachelor.project.Filters;
//
//
//import javax.servlet.Filter;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//public class CSPFilter implements Filter {
//
//
//    public static final String POLICY = "default-src http://localhost:8080 'none'; object-src 'none'";
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain)
//            throws IOException, ServletException {
//        if (response instanceof HttpServletResponse) {
//            ((HttpServletResponse)response).setHeader("Content-Security-Policy", CSPFilter.POLICY);
//        }
//    }
//
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException { }
//
//    @Override
//    public void destroy() { }
//
//}