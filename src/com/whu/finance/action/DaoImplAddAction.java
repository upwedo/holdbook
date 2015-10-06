package com.whu.finance.action;

import javax.servlet.ServletContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whu.finance.util.ConstantUtil;
import com.whu.finance.util.StringUtil;

public class DaoImplAddAction extends ActionSupport {
	private String dp_entityName;
	private String dp_daoName;
	private String dp_daoImplName;
	private String dp_DaoImplRepository;
	private String addDaoImplTip;
	private String errorTip;

	public String getDp_entityName() {
		return dp_entityName;
	}

	public void setDp_entityName(String dp_entityName) {
		this.dp_entityName = dp_entityName;
	}

	public String getDp_daoName() {
		return dp_daoName;
	}

	public void setDp_daoName(String dp_daoName) {
		this.dp_daoName = dp_daoName;
	}

	public String getDp_daoImplName() {
		return dp_daoImplName;
	}

	public void setDp_daoImplName(String dp_daoImplName) {
		this.dp_daoImplName = dp_daoImplName;
	}

	public String getDp_DaoImplRepository() {
		return dp_DaoImplRepository;
	}

	public void setDp_DaoImplRepository(String dp_DaoImplRepository) {
		this.dp_DaoImplRepository = dp_DaoImplRepository;
	}

	public String getAddDaoImplTip() {
		return addDaoImplTip;
	}

	public void setAddDaoImplTip(String addDaoImplTip) {
		this.addDaoImplTip = addDaoImplTip;
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
		if (dp_entityName == null || dp_entityName.equals("")) {
			setErrorTip("操作失败");
			return ERROR;
		}
		//daoimpl是否为空
		if (dp_daoImplName == null || dp_daoImplName.equals("")) {
			dp_daoImplName = dp_entityName + "DaoImpl";
		}
		//声明的dao是否为空
		if (dp_DaoImplRepository == null || dp_DaoImplRepository.equals("")) {
			dp_DaoImplRepository = dp_entityName.substring(0, 1).toLowerCase()
					+ dp_entityName.substring(1, dp_entityName.length())
					+ "Dao";
		}

		String daoImplJava = "package com.whu.finance.dao.impl;\n"
				+ " import javax.annotation.Resource;\n"
				+ "import org.hibernate.SessionFactory;\n"
				+ "import org.springframework.stereotype.Repository;\n"
				+ "import com.whu.finance.entity." + dp_entityName + ";\n"
				+ "@Repository(\"" + dp_DaoImplRepository + "\")\n"
				+ "public class " + dp_daoImplName + " extends BaseDaoImpl<"
				+ dp_entityName + ">{\n"
				+ "\t@Resource(name=\"sessionFactory\")\n"
				+ "\tprivate SessionFactory sessionFactory;\n" + "}";
		StringUtil.StringToJavaFile(daoImplJava, ConstantUtil.PACKAGEPATH
				+ "/dao/impl/" + dp_daoImplName + ".java", "gbk");
		ctx.getSession().put(ConstantUtil.P_DAOIMPL, dp_daoImplName);
		setAddDaoImplTip(dp_daoImplName + "添加成功！！");
		return SUCCESS;
	}
}
