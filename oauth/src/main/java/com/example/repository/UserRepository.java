package com.example.repository;

import com.example.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xielongwang
 * @create 2018-08-07 下午9:15
 * @email xielong.wang@nvr-china.com
 * @description
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    /**
     *
     * @param userName
     * @return
     */
    UserEntity findByUserName(String userName);


}
