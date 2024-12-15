package com.example.ai_store.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter("/*")  // Áp dụng cho tất cả các yêu cầu
public class CORSFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Thiết lập các header CORS
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Nếu là yêu cầu OPTIONS (preflight), trả về 200 OK
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);  // Trả về mã 200 OK cho preflight request
        } else {
            chain.doFilter(request, response);  // Tiếp tục chuỗi filter
        }
    }

    @Override
    public void destroy() {
        // Dọn dẹp nếu cần
    }
}
