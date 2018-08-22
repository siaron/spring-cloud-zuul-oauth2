package com.example.security.handler;

import com.common.dto.WrapMapper;
import com.example.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * APP环境下认证失败处理器
 *
 * @author xielong.wang
 */
@Component("pcAuthenticationFailureHandler")
public class PcAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * On authentication failure.
     *
     * @param request   the request
     * @param response  the response
     * @param exception the exception
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("{\"status\":\"error\",\"msg\":\"");
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            sb.append("用户名或密码输入错误，登录失败!");
        } else if (exception instanceof DisabledException) {
            sb.append("账户被禁用，登录失败，请联系管理员!");
        } else {
            sb.append("登录失败!");
        }
        sb.append("\"}");
        out.write(sb.toString());
        out.flush();
        out.close();
    }

}
