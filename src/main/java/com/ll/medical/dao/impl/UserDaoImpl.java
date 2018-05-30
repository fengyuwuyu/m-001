package com.ll.medical.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.ll.medical.dao.IUserDao;
import com.ll.medical.model.User;
import com.ll.medical.model.UserQuery;
import com.ll.medical.repository.UserReposity;
import com.ll.medical.util.StringUtil;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private UserReposity userReposity;

	@Override
	public User save(User user) {
		return userReposity.save(user);
	}

	@Override
	public User findById(Integer id) {
		return userReposity.findOne(id);
	}

	@Override
	public Page<User> findAll(UserQuery userQuery) {
		Specification<User> specification = new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtil.isNullEmpty(userQuery.getUsername())) {
					predicates.add(cb.equal(root.get("userName").as(String.class), "%" + userQuery.getUsername() + "%"));
				}
				if (userQuery.getRoleId() != null) {
					predicates.add(cb.equal(root.get("roleId").as(Integer.class), userQuery.getRoleId()));
				}
				Predicate[] arrayPredicates = new Predicate[predicates.size()];
				predicates.toArray(arrayPredicates);
				return cb.and(arrayPredicates);
			}
		};
		return userReposity.findAll(specification, new PageRequest(userQuery.getPageNumber(), userQuery.getPageSize(),
				new Sort(Direction.DESC, "updateDate")));
	}

}
