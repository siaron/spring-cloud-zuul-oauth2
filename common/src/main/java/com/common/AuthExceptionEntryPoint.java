package com.common;

import com.common.dto.WrapMapper;
import com.common.dto.Wrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取资源时认证失败。返回错误信息
 *
 * @author xielong.wang
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

    private final ObjectMapper objectMapper;


    public AuthExceptionEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Wrapper<Object> errMapper = WrapMapper.wrap(10001, "错误的token");
        try {
            objectMapper.writeValue(response.getOutputStream(), errMapper);
        } catch (Exception e) {
            logger.error("get resource error!", e);
            throw new ServletException();
        }
    }
}