package com.hold.book.action;

import javax.servlet.ServletContext;

import com.hold.book.util.ConstantUtil;
import com.hold.book.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EntityAddAction extends ActionSupport {
	private String t_entityName;
	private String t_name;
	private String[] t_field;
	private String[] t_type;
	private String[] t_size;
	private String[] t_key;
	private String[] t_note;
	private String addEntityTip;
	private String errorTip;

	public String getT_entityName() {
		return t_entityName;
	}

	public void setT_entityName(String t_entityName) {
		this.t_entityName = t_entityName;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String[] getT_field() {
		return t_field;
	}

	public void setT_field(String[] t_field) {
		this.t_field = t_field;
	}

	public String[] getT_type() {
		return t_type;
	}

	public void setT_type(String[] t_type) {
		this.t_type = t_type;
	}

	public String[] getT_size() {
		return t_size;
	}

	public void setT_size(String[] t_size) {
		this.t_size = t_size;
	}

	public String[] getT_key() {
		return t_key;
	}

	public void setT_key(String[] t_key) {
		this.t_key = t_key;
	}

	public String[] getT_note() {
		return t_note;
	}

	public void setT_note(String[] t_note) {
		this.t_note = t_note;
	}

	public String getAddEntityTip() {
		return addEntityTip;
	}

	public void setAddEntityTip(String addEntityTip) {
		this.addEntityTip = addEntityTip;
	}

	public String getErrorTip() {
		return errorTip;
	}

	public void setErrorTip(String errorTip) {
		this.errorTip = errorTip;
	}

	public String execute() {
		if (t_entityName == null || t_entityName.equals("") || t_name == null
				|| t_name.equals("")) {
			setErrorTip("操作失败！！");
			return ERROR;
		}
		t_entityName = t_entityName.substring(0, 1).toUpperCase()
				+ t_entityName.substring(1, t_entityName.length());
		ActionContext ctx = ActionContext.getContext();
		String entityJava = "package com.hold.book.entity;\n"
				+ " import java.util.Date;\n"
				+ "import javax.persistence.Entity;\n"
				+ "import javax.persistence.Id;\n"
				+ "import javax.persistence.Table;\n" + "@Entity\n"
				+ "@Table(name=\"" + t_name + "\")\n" + "public class "
				+ t_entityName + "{\n";

		// 变量声明
		for (int i = 0; i < t_field.length && !t_field[i].equals(""); i++) {
			entityJava += "\tprivate "
					+ t_type[i]
					+ " "
					+ t_field[i]
					+ ";"
					+ ((t_note.length > i && !t_note[i].equals("")) ? "//"
							+ t_note[i] + "\n" : "\n");
		}

		entityJava += "\t@Id\n";
		// get、set
		for (int i = 0; i < t_field.length && !t_field[i].equals(""); i++) {
			// get
			entityJava += "\tpublic " + t_type[i] + " " + "get"
					+ t_field[i].substring(0, 1).toUpperCase()
					+ t_field[i].substring(1, t_field[i].length()) + "(){\n"
					+ "\t\treturn " + t_field[i] + ";\n\t}\n";
			// set
			entityJava += "\tpublic void set"
					+ t_field[i].substring(0, 1).toUpperCase()
					+ t_field[i].substring(1, t_field[i].length()) + "("
					+ t_type[i] + " " + t_field[i] + "){\n" + "\t\tthis."
					+ t_field[i] + " = " + t_field[i] + ";\n\t}\n";
		}
		entityJava += "}";
		System.out.println( ConstantUtil.PACKAGEPATH);
		StringUtil.StringToJavaFile(entityJava, ConstantUtil.PACKAGEPATH
				+ "/entity/" + t_entityName + ".java", "gbk");
		ctx.getSession().put(ConstantUtil.P_ENTITY, t_entityName);
		setAddEntityTip(t_entityName + "添加成功！！");
		return SUCCESS;
	}
}
