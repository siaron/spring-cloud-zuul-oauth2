package com.example.repository;

import com.example.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {


    List<ActionEntity> findByUId(Long uid);

}
