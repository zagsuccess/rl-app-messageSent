package com.uhope.messageSent.filter;

import com.uhope.base.constants.Constant;
import com.uhope.base.enums.EnumEnvType;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 再过滤器中添加token
 *
 * @author ChenBin on 2018/09/04
 */
public class AddTokenFilter implements Filter {

    /**
     * superAdmin
     */
    //超级管理员
//    private static final String DEFAULT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpblRpbWUiOjE1MzAyNjA4NzUzNTgsImFwcElkIjoiMTIzNDU2IiwidXNlcklkIjoiOGQ2YmQ1NmMyNmIzMTFlOGFiMTFhYTZhMGNhYmU4YmEifQ.wiAEKBPrRVxLXgrMsfiZMRz-VSyO8qDONnLfncTYXbc";
    //天津市河长办
    private static final String DEFAULT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpblRpbWUiOjE1NDM4MzI2NTM3NzIsImFwcElkIjoiN2MzOWI0MzFkOTBjNGY5YTgxYTMxN2I3ODdjODQ3NzAiLCJ1c2VySWQiOiJlNGYxMzBhN2FhY2E0MDY1ODhmMWY4NTEyZmI1YjY2YiIsInRlcm1pbmFsVHlwZSI6IldFQiJ9.9Ma-qbm6fgucYSjb5ogRxIGlVa-K3Q1Ca3f_uPVwoKk";
    //河北区河长办
    /*private static final String DEFAULT_TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJsb2dpblRpbWUiOjE1NDM5MDczNzk1MzcsImFwcElkIjoiN2MzOWI0MzFkOTBjNGY5YTgxYTMxN2I3ODdjODQ3NzAiLCJ1c2VySWQiOiIzZTE3ZjNmMjRhYmU0ZTIwYWMxNjAxYjUzNTZmMGY2NyIsInRlcm1pbmFsVHlwZSI6IldFQiJ9.ZdDqBRX1TICMtWkXdY5MgIYiTXNrM963Jl8Ukenz0VY";*/
    private String profilesActive;

    public AddTokenFilter(String profilesActive) {
        super();
        this.profilesActive = profilesActive;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (profilesActive == null || !EnumEnvType.DEV.toString().equalsIgnoreCase(profilesActive)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(new CustomeizedRequest((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
        // do nothing

    }

    private class CustomeizedRequest extends HttpServletRequestWrapper {

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request
         * @throws IllegalArgumentException if the request is null
         */
        public CustomeizedRequest(HttpServletRequest request) {
            super(request);
        }


        @Override
        public String getHeader(String name) {
            if (!Constant.HTTP_HEADER_ACCESS_TOKEN.equalsIgnoreCase(name)) {
                return super.getHeader(name);
            }
            String token = super.getHeader(name);
            return StringUtils.isNotBlank(token) ? token : DEFAULT_TOKEN;
        }
    }

}
