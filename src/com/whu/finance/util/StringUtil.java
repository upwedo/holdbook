package com.whu.finance.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;



public class StringUtil {
	/**
	 * ��ʱ��ת��String
	 * @param calendarDay
	 * @param dateFormat
	 * @param offset
	 * @return
	 */
	public static String getStringDate(int calendarDay,String dateFormat,int offset){
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		Calendar c=Calendar.getInstance();
		c.add(calendarDay, offset);
		Date date=c.getTime();
		return sdf.format(date);
	}
	
	/**
	 * ��ʱ��ת��String
	 * @param calendarDay
	 * @param dateFormat
	 * @param offset
	 * @return
	 */
	public static String getStringDate(Date date,String dateFormat){
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	/**
	 * ����ǰdate��ʽת������Ҫ�����ڸ�ʽ
	 * @param date
	 * @param dateFormat ����Ҫת���ɵ����ڸ�ʽ
	 * @return
	 */
	public static Date dateFormatTransform(Date date,String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String tmp = sdf.format(date);
		Date result = null;
		try {
			result = sdf.parse(tmp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��properties��ȡ������
	 * @param file
	 * @param property
	 * @return
	 */
	public static String getFromDate(String file,String property){
		InputStream is = null;
		Properties pro = new Properties();
		try {
			is = new BufferedInputStream(new FileInputStream(file)); 
			pro.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pro.getProperty(property);
	}
	
	/**
	 * ��properties�д���ʱ��
	 * @param file
	 * @param property
	 * @param date
	 */
	public static void storeDate(String file,String property, String date) {

		Properties pro = new Properties();
		try {
			pro.load(new BufferedInputStream(new FileInputStream(file)));  
			FileOutputStream fos = new FileOutputStream(file);
			pro.setProperty(property, date);
			pro.store(fos, "update THdate value");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��Stringת��float����
	 * @param s
	 * @return
	 */
	public static float strToFloat(String s) {
		if (null == s || s.length() == 0 || s.contains("None"))
			return 0.0f;
		if (s.matches("[-\\s]+"))
			return 0.0f;
		else
			return Float.parseFloat(new String(s));
	}
	
	/**
	 * ��Stringת��double����
	 * @param s
	 * @return
	 */
	public static double strToDouble(String s) {
		if (null == s || s.length() == 0 || s.contains("None"))
			return 0.0d;
		if (s.matches("[-\\s]+"))
			return 0.0d;
		else
			return Double.parseDouble(new String(s));
		
	}
	
	/**
	 * ��Stringת��long����
	 * @param s
	 * @return
	 */
	public static long strToLong(String s) {
		if (null == s || s.length() == 0)
			return 0l;
		if (s.matches("[-\\s]+"))
			return 0l;
		else
			return Long.parseLong(new String(s));
	}
	
	/**
	 * Stringת��int����
	 * @param s
	 * @return
	 */
	public static int strToInt(String s) {
		if (null == s || s.length() == 0)
			return 0;
		if (s.matches("[-\\s]+"))
			return 0;
		else
			return Integer.parseInt(new String(s));
	}
	
	/**
	 * doubleתString
	 * @param d
	 * @param num ����С��λ��
	 * @return
	 */
	public static String doubleToStr(double d,int num){
		
		double factor = Math.pow(10, num);
		d = (double)Math.round(d*factor)/factor;
	    return Double.toString(d);
	}
	
	/**
	 * �ж�һ���ַ����Ƿ���Ӣ�Ŀ�ͷ
	 * @param str
	 * @return
	 */
	public static boolean strIsEnglish(String str){
		boolean sign = false;
		if ((str.charAt(0) >= 'A' && str.charAt(0) <= 'Z')  
               || (str.charAt(0) >= 'a' && str.charAt(0) <= 'z'))
			sign = true;
		
		return sign;
	}
	//��ȡһ���ַ������ֽڽ�ȡ�ִ�
	public static String subStrParse(String t,int k){  
        //Ҫ��ʾ����С���ֽ�,������ȫ����ʾ  
        if(k<=t.getBytes().length)  
            //��0,����k���ַ�  
            for (int i = 0; i < k; i++) {  
                //��i���ַ�,����ֽڳ���==2��Ϊ����,k--  
                String temp = t.substring(i, i + 1);  
                if (temp.getBytes().length == 2)  
                    k--;  
            }  
        else  
            k = t.length();  
        return t.substring(0,k);  
    } 
	
	/** 
     * ȥ���ַ����е�htmlԴ�롣<br>    
     * @param con  ����  

     * @param length  ��ȡ����   

     * @param end   ԭʼ�ַ���������ȡ����ʱ�����������ַ� 
      * @return ȥ���������  
     */

    public static String subStringHTML(String con,int length,String end) {
        String content = "";
        if(con!=null){
            content=con.replaceAll("</?[^>]+>","");//�޳���<html>�ı�ǩ
            content=content.replace("&nbsp;","");
            content=content.replace(".","");
            content=content.replace("\"","��");
            content=content.replace("'","��");
            if(content.length()>length){
             content=content.substring(0,length)+end;
            }
        }

        return content;
      }
    /**
     * 
     * @param content д���ļ����ַ���
     * @param filepath д���ļ�·��
     * @param charset �ļ�����
     */
    public static void StringToJavaFile(String content,String filepath,String charset) {
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        File f = new File(filepath);
	           if(!f.getParentFile().exists()){
	        	    if(!f.getParentFile().mkdirs()) {
	        	    	 return;
	                }
	        	}			       	           
	           FileOutputStream fos;
			try {
				fos = new FileOutputStream(f);
			
			    InputStream bis1 =  new ByteArrayInputStream(content.getBytes(charset));
			    int len = 1024;
				byte[] b = new byte[len];
				 while ((len = bis1.read(b)) != -1)
				  {
				        fos.write(b, 0, len);
				   }
				    fos.flush();
				    bis1.close();		 	   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
    /**
     * ���ļ�
     * @param filePath
     * @return
     */
    public static String readFileByLines(String filePath) {
        File file = new File(filePath);
        if(!file.exists()){//�ж��ļ��Ƿ����
        	return null;
        }
        BufferedReader reader = null;
        String fileContent="";//��ȡ��������
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
         
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
               fileContent+=tempString;
                line++;
            }
            reader.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileContent;
    }
    public static String readFileByLine(String filePath) {
        File file = new File(filePath);
        if(!file.exists()){//�ж��ļ��Ƿ����
        	return null;
        }
        BufferedReader reader = null;
        String fileContent="";//��ȡ��������
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
         
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
               fileContent+=tempString+"\n";
                line++;
            }
            reader.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileContent;
    }
}

