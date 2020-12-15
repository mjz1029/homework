package com.mjz.util;

import java.util.Date;


public class SnGenerateUtil {
	public static String generateSn(int departmentId){
		String sn = "";
		sn = "S" + departmentId + System.currentTimeMillis();
		return sn;
	}
	public static String generateStaff1Sn(int departmentId){
		String sn = "";
		sn = "T" + departmentId + System.currentTimeMillis();
		return sn;
	}
}
