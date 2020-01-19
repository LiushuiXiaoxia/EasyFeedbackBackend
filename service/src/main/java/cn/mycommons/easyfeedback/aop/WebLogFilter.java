package cn.mycommons.easyfeedback.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "webLog")
public class WebLogFilter implements Filter {

    private static final String FILTER_APPLIED = "__spring_security_WebLogFilter_filterApplied";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // spring security 不执行
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute(FILTER_APPLIED, true);

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String method = httpServletRequest.getMethod();
        String uri = httpServletRequest.getRequestURI();

        int code = -1;
        long begin = System.currentTimeMillis();
        try {
            log.info("begin filter {} --> {}", method, uri);

            chain.doFilter(request, response);

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            code = httpServletResponse.getStatus();
        } finally {
            long time = System.currentTimeMillis() - begin;
            log.info("finish filter log({}) {} -> {} {}", time, method, uri, code);
        }
    }

    @Override
    public void destroy() {

    }
}