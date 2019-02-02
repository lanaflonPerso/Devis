package com.devis.config;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
  * Servlet Filter implementation class EncodingFilter
  */
@WebFilter("/EncodingFilter")
public class EncodingFilter extends SetCharacterEncodingFilter {

    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}