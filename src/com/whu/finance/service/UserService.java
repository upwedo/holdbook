package com.whu.finance.service;

import com.whu.finance.entity.User;

public interface UserService extends BaseService<User>{
	/**
	 * ��֤�û������ؽ�ɫ
	 * @param hql
	 * @param objects
	 * @return system:ϵͳ�û�,common:��ͨ�û�,error:��֤ʧ��
	 */
    public String validLogin(String hql, Object...objects );
}
