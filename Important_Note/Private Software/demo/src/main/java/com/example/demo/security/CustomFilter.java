package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.web.csrf.CsrfToken;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CustomFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        CsrfToken csrf = (CsrfToken) request.getAttribute("_csrf");
        log.info(" doFilter {} == {} == {}", csrf.getHeaderName(), csrf.getParameterName(), csrf.getToken());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
