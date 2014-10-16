package com.cognizant.cdc.filter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.*

class ReferrerCheckFilter implements Filter {

    private List<String> excludedPaths = []

    @Override
    void init(FilterConfig filterConfig) {}

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        HttpServletRequest httpServletRequest = ((HttpServletRequest) request)
        HttpServletResponse httpServletResponse = ((HttpServletResponse) response)

        String referrer = httpServletRequest.getHeader("referer")
        String requestURL = httpServletRequest.getRequestURL()

        String host = new URL(requestURL).getHost()

        if(requireReferrerCheck(requestURL)) {
            if(referrer == null || !referrer.contains("://" + host)) {
                httpServletResponse.sendError(403, "Can't access cross site!")
                return
            }
        }

        chain.doFilter(request, response)
    }


    private boolean requireReferrerCheck(String requestURI) {

        boolean requireReferrerCheck = true

        excludedPaths.each {
            String path ->
            if (requestURI.contains(path)) {
                requireReferrerCheck = false
                return
            }
        }

        requireReferrerCheck
    }

    public void setExcludedPaths(List<String> excludedPaths) {
        this.excludedPaths = excludedPaths
    }

    @Override
    void destroy() {}
}
