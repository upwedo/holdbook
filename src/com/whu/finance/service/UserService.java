package com.whu.finance.service;

import com.whu.finance.entity.User;

public interface UserService extends BaseService<User>{
	/**
	 * 验证用户，返回角色
	 * @param hql
	 * @param objects
	 * @return system:系统用户,common:普通用户,error:验证失败
	 */
    public String validLogin(String hql, Object...objects );
}
