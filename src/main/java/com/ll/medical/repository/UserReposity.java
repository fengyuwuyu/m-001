package com.ll.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ll.medical.model.User;

public interface UserReposity extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}
