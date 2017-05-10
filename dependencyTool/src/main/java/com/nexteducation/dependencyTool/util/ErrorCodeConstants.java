package com.nexteducation.dependencyTool.util;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeConstants {

	static Map<String, String> errorCodes=new HashMap<String, String>();
	
	static{
		errorCodes.put("NA100", "Request Object found null.");
		errorCodes.put("NA101", "SessionParams not found.");
	}

	public static String getError(String code) {
		return errorCodes.get(code);
	}
}
