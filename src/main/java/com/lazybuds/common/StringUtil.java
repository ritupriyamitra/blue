package com.lazybuds.common;

public class StringUtil {
	
	public static boolean isNullOrEmpty(String value) {
		if(value==null || "".equals(value.trim())) {
			return true;
		}
		return false;
		
	}

}
