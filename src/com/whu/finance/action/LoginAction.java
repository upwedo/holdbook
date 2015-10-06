package com.whu.finance.action;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whu.finance.entity.User;
import com.whu.finance.service.UserService;
import com.whu.finance.util.ConstantUtil;

public class LoginAction extends ActionSupport implements ServletResponseAware{
	@Resource(name="userService")
     private UserService userService;
	 private User user;
	 private String rmb;
	 private HttpServletResponse response;
		@Override
		public void setServletResponse(HttpServletResponse response) {
			this.response=response;
		}
		/**
		 * 设置cookie
		 * @param user
		 */
		public void setCookie(User user){
			Cookie cookie= new Cookie(ConstantUtil.USER_COOKIE, user.getUsername()+","+user.getPwd());
			cookie.setMaxAge(60*60*24*14);//设置两周
			response.addCookie(cookie);
		}
		
	  public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public String getRmb() {
			return rmb;
		}
		public void setRmb(String rmb) {
			this.rmb = rmb;
		}
	public String execute(){
		 ActionContext ctx =ActionContext.getContext();
		 String role = userService.validLogin("from User user where user.username=? and user.pwd=?",user.getUsername(),user.getPwd());
		 if(!role.equals("error")){
	    	 ctx.getSession().put(ConstantUtil.USER,user.getUsername() );
	    	 ctx.getSession().put(ConstantUtil.ROLE, role);
	    	 if(rmb!=null){
	    		 setCookie(user);
	    	 }
	     }
		 return role;
	 }


}
