package com.example;

import com.example.feign.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Shenluw 创建日期：2018/6/27 16:52
 */
@FeignClient(value = "iot", configuration = FeignClientConfig.class)
public interface IotFeignClient {

    @GetMapping("/api/iot/time")
    String authTime();

}
