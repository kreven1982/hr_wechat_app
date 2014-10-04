package com.cognizant.cdc.util

class UUIDUtil {
	public static String getUUID(){
		String s = java.util.UUID.randomUUID().toString()
		return s.replaceAll("-", "");
	}
	
	public static void main(args) {
        (1..10).each{
			println getUUID()
		}
	}
}
