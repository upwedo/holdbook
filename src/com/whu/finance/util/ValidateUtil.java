package com.whu.finance.util;

import java.util.Collection;

import org.jsoup.select.Elements;

public class ValidateUtil {
	/**
	 * �ַ����Ƿ���Ч
	 * @param str
	 * @return
	 */
	public static boolean isValidate(String str){
		if(str!=null&&!"".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * �����Ƿ���Ч
	 * @param col
	 * @return
	 */
	public static boolean isValidate(Collection col){
		if(col!=null&&col.size()!=0){
			return true;
		}
		return false;
	}
	
	public static boolean isValidate(Elements elements){
		if(elements!=null&&elements.size()!=0){
			return true;
		}
		return false;
	}
}
