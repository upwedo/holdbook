package com.whu.finance.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.whu.finance.dao.BaseDao;
import com.whu.finance.entity.User;
import com.whu.finance.service.UserService;

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
