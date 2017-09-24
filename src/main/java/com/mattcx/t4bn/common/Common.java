package com.mattcx.t4bn.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Common {
	
    /** 
     * 時間轉換工具, format EX: yyyy-MM-dd HH:mm:ss
     * --
     *  
     * */		
	public static String timestampFormat(Timestamp now, String format) {		
		if(null==now) { return ""; }
		
		String resultStr = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			resultStr = df.format(now);
		} catch(Exception e) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			resultStr = df.format(now);			
		}
		return resultStr;
	}
	
}
