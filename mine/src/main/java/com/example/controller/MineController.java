package com.example.controller;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xielongwang
 * @create 2018-08-05 下午5:46
 * @email xielong.wang@nvr-china.com
 * @description
 */
@RestController
@RequestMapping("api/mine")
public class MineController {

    @GetMapping("/time")
    public String test() {
        return "mine " + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }
}