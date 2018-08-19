package com.example.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * @author xielongwang
 * @create 2018-08-08 上午9:13
 * @email xielong.wang@nvr-china.com
 * @description
 */
public class PasswordEncoderTest {

    @Test
    public void testPw() {

        final String pass = new BCryptPasswordEncoder().encode("admin");

        System.out.println(pass);


        System.out.println(new String(Base64.encode("cloudAuth:cloudAuthSecret".getBytes())));
    }
}
