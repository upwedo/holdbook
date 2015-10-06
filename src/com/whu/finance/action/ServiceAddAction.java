package com.whu.finance.action;

import javax.servlet.ServletContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whu.finance.util.ConstantUtil;
import com.whu.finance.util.StringUtil;

public class ServiceAddAction extends ActionSupport {
	private String s_entityName;
	private String s_daoName;
	private String s_daoImplName;
	private String s_serviceName;
	private String sucessTip;
	private String errorTip;

	public String getS_entityName() {
		return s_entityName;
	}

	public void setS_entityName(String s_entityName) {
		this.s_entityName = s_entityName;
	}

	public String getS_daoName() {
		return s_daoName;
	}

	public void setS_daoName(String s_daoName) {
		this.s_daoName = s_daoName;
	}

	public String getS_daoImplName() {
		return s_daoImplName;
	}

	public void setS_daoImplName(String s_daoImplName) {
		this.s_daoImplName = s_daoImplName;
	}

	public String getS_serviceName() {
		return s_serviceName;
	}

	public void setS_serviceName(String s_serviceName) {
		this.s_serviceName = s_serviceName;
	}

	public String getSucessTip() {
		return sucessTip;
	}

	public void setSucessTip(String sucessTip) {
		this.sucessTip = sucessTip;
	}

	public String getErrorTip() {
		return errorTip;
	}

	public void setErrorTip(String errorTip) {
		this.errorTip = errorTip;
	}

	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		//entity是否为空
		if (s_entityName == null || s_entityName.equals("")) {
			setErrorTip("操作失败");
			return ERROR;
		}
		//service是否为空
		if (s_serviceName == null || s_serviceName.equals("")) {
			s_serviceName = s_entityName + "Service";
		}

		String serviceJava = "package com.whu.finance.service;\n"
				+ "import com.whu.finance.entity." + s_entityName + ";\n"
				+ "public interface " + s_serviceName + " extends BaseService<"
				+ s_entityName + ">{\n" + "}";
		StringUtil.StringToJavaFile(serviceJava, ConstantUtil.PACKAGEPATH
				+ "/service/" + s_serviceName + ".java", "gbk");
		ctx.getSession().put(ConstantUtil.P_SERVICE, s_serviceName);
		setSucessTip(s_serviceName + "添加成功！！");
		return SUCCESS;
	}
}
