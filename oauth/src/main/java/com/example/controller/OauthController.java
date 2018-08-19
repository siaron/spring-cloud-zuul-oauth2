package com.example.controller;

import com.common.dto.WrapMapper;
import com.common.dto.Wrapper;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

}
