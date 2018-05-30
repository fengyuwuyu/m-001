package com.ll.medical.dao;

import org.springframework.data.domain.Page;

import com.ll.medical.model.User;
import com.ll.medical.model.UserQuery;

public interface IUserDao {

	User save(User user);
	
	User findById(Integer id);
	
	Page<User> findAll(UserQuery query);
	
	
}
