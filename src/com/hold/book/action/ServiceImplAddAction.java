package com.hold.book.action;

import javax.servlet.ServletContext;

import com.hold.book.util.ConstantUtil;
import com.hold.book.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ServiceImplAddAction extends ActionSupport {
	private String sp_entityName;
	private String sp_daoName;
	private String sp_daoImplName;
	private String sp_serviceName;
	private String sp_serviceImplName;
	private String sp_service;
	private String sp_dao;
	private String sucessTip;
	private String errorTip;

	public String getSp_entityName() {
		return sp_entityName;
	}

	public void setSp_entityName(String sp_entityName) {
		this.sp_entityName = sp_entityName;
	}

	public String getSp_daoName() {
		return sp_daoName;
	}

	public void setSp_daoName(String sp_daoName) {
		this.sp_daoName = sp_daoName;
	}

	public String getSp_daoImplName() {
		return sp_daoImplName;
	}

	public void setSp_daoImplName(String sp_daoImplName) {
		this.sp_daoImplName = sp_daoImplName;
	}

	public String getSp_serviceName() {
		return sp_serviceName;
	}

	public void setSp_serviceName(String sp_serviceName) {
		this.sp_serviceName = sp_serviceName;
	}

	public String getSp_serviceImplName() {
		return sp_serviceImplName;
	}

	public void setSp_serviceImplName(String sp_serviceImplName) {
		this.sp_serviceImplName = sp_serviceImplName;
	}

	public String getSp_service() {
		return sp_service;
	}

	public void setSp_service(String sp_service) {
		this.sp_service = sp_service;
	}

	public String getSp_dao() {
		return sp_dao;
	}

	public void setSp_dao(String sp_dao) {
		this.sp_dao = sp_dao;
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
		if (sp_entityName == null || sp_entityName.equals("")) {
			setErrorTip("操作失败");
			return ERROR;
		}
		//service是否为空
		if (sp_serviceName == null || sp_serviceName.equals("")) {
			sp_serviceName = sp_entityName + "Service";
		}
      //serviceImpl是否为空
		if (sp_serviceImplName == null || sp_serviceImplName.equals("")) {
			sp_serviceImplName = sp_entityName + "ServiceImpl";
		}
		//声明的service是否为空
		if (sp_service == null || sp_service.equals("")) {
			sp_service = sp_entityName.substring(0, 1).toLowerCase()
					+ sp_entityName.substring(1, sp_entityName.length())
					+ "Service";
		}
		//注入的dao是否为空
		if (sp_dao == null || sp_dao.equals("")) {
			sp_dao = sp_entityName.substring(0, 1).toLowerCase()
					+ sp_entityName.substring(1, sp_entityName.length())
					+ "Dao";
		}
		String serviceImplJava = "package com.hold.book.service.impl;\n"
				+ "import javax.annotation.Resource;\n"
				+ "import org.springframework.stereotype.Service;\n"
				+ "import com.hold.book.dao.BaseDao;\n"
				+ "import com.hold.book.entity."
				+ sp_entityName
				+ ";\n"
				+ "import com.hold.book.service."
				+ sp_serviceName
				+ ";\n"
				+ "@Service(\""
				+ sp_service
				+ "\")\n"
				+ "public class "
				+ sp_serviceImplName
				+ " extends BaseServiceImpl<"
				+ sp_entityName
				+ "> implements "
				+ sp_serviceName
				+ "{\n"
				+ "\tpublic "
				+ sp_serviceImplName
				+ "(){\n"
				+ "\t\tsuper();\n\t}\n"
				+ "\t@Resource(name=\""
				+ sp_dao
				+ "\")\n"
				+ "\tpublic void setBaseDao(BaseDao<"
				+ sp_entityName
				+ "> baseDao){\n"
				+ "\t\tsuper.setBaseDao(baseDao);\n\t}\n"
				+ "\t@Override\n\tpublic void start() {\n\t\t// TODO Auto-generated method stub\n\n\t}\n"
				+ "}";
		StringUtil.StringToJavaFile(serviceImplJava, ConstantUtil.PACKAGEPATH
				+ "/service/impl/" + sp_serviceImplName + ".java", "gbk");
		ctx.getSession().put(ConstantUtil.P_SERVICEIMPL, sp_serviceImplName);
		setSucessTip(sp_serviceImplName + "添加成功！！");
		return SUCCESS;
	}
}
