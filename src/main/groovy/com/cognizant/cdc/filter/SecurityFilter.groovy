 package com.cognizant.cdc.filter

import com.cognizant.cdc.model.User
import com.cognizant.cdc.service.UserService
import com.cognizant.cdc.util.UserSession
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@CompileStatic
@TypeChecked
class SecurityFilter implements Filter {

    private List<Map<String, String>> authIncludedPaths = []
    private int statusCode

    @Autowired
    UserService userService

    @Override
    void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = ((HttpServletRequest) request)
        HttpServletResponse httpServletResponse = ((HttpServletResponse) response)

        httpServletRequest.setCharacterEncoding("UTF-8")
        httpServletResponse.setCharacterEncoding("UTF-8")

        String requestURI = httpServletRequest.getRequestURI()
        String method = httpServletRequest.getMethod()

        if (requireAuth(requestURI, method)) {
            User user = null
            String token = null
            Cookie[] cookies = httpServletRequest.getCookies();

            cookies.each {
                Cookie cookie ->
                if ("token".equals(cookie.name)) {
                    token = cookie.value

                    user = userService.getUserByToken(token)
                    UserSession.setUser(user)
                    return
                }
            }

            if (user == null) {
                httpServletResponse.sendError(statusCode, "You don't have permission")
                return;
            }
        }

        chain.doFilter(request, response)
    }

    @Override
    void destroy() {}

    private boolean requireAuth(String requestURI, String reqMethod) {

        boolean requireAuth = false

        authIncludedPaths.each {
            Map pathConfig ->
            if (requestURI.contains(pathConfig['path'] as String) && reqMethod.equalsIgnoreCase(pathConfig['method'] as String)) {
                requireAuth = true
                return
            }
        }

        requireAuth
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode
    }

    public void setIncludedPaths(List<Map> includedPaths) {
        this.authIncludedPaths = includedPaths
    }
}
