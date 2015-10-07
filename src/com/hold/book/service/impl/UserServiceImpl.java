package com.hold.book.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hold.book.dao.BaseDao;
import com.hold.book.entity.User;
import com.hold.book.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	public UserServiceImpl(){
		super();
	}
	@Resource(name="userDao")
	public void setBaseDao(BaseDao<User> baseDao){
		super.setBaseDao(baseDao);
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String validLogin(String hql, Object...objects) {
		User user=(User) this.getBaseDao().uniqueResult(hql, objects);
		if(user!=null){
			return user.getRole();
		}
		return "error";
	}

}
