package com.book.util;

public class StringUtil {

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		
		if(str == null || "".equals(str.trim())) {//trim()��������ȥ���ַ������˵Ķ���Ŀո�
			return true;   						//ע�⣬�����˵Ŀո����������˵Ŀո��ж��ٸ�����ȥ����
		}else {									// ��Ȼ���м����Щ�ո񲻻ᱻȥ��
			return false;
		}
	}
	/**
	 * �жϲ�Ϊ��
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
