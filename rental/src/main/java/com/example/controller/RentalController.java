package com.example.controller;


import com.example.IotFeignClient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/rental")
public class RentalController {

    @Autowired
    private IotFeignClient iotFeignClient;

    @GetMapping("time")
    public String test() {
        return "rental " + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    @GetMapping("iot")
    public String iot() {
        return iotFeignClient.authTime();
    }

}