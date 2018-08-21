package com.example.controller;

import com.common.dto.WrapMapper;
import com.common.dto.Wrapper;
import io.undertow.servlet.util.SavedRequest;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author xielongwang
 * @create 2018-08-05 下午5:47
 * @email xielong.wang@nvr-china.com
 * @description
 */
@RestController
@RequestMapping
public class OauthController {

    @GetMapping("/api/time")
    public Wrapper<Date> test() {
        return WrapMapper.ok(new Date());
    }

    @RequestMapping("/auth/require")
    public Wrapper requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return WrapMapper.error("访问的服务需要身份认证");
    }
}
