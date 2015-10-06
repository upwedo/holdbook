package com.whu.finance.util;

import java.util.Collection;

import org.jsoup.select.Elements;

public class ValidateUtil {
	/**
	 * 字符串是否有效
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
	 * 集合是否有效
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
