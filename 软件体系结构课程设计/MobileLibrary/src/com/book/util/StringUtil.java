package com.book.util;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		
		if(str == null || "".equals(str.trim())) {//trim()的作用是去掉字符串两端的多余的空格
			return true;   						//注意，是两端的空格，且无论两端的空格有多少个都会去掉，
		}else {									// 当然，中间的那些空格不会被去掉
			return false;
		}
	}
	/**
	 * 判断不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		
		if(str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
}
