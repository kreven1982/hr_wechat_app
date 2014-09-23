package com.cognizant.cdc.model

import java.util.Map;

class Content implements Entity<Content>{

	long id;
	String uniqueKey;
	String title;
	String content;
	//String 
	
	
	@Override
	public Map toDBMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fromDBMap(Map map) {
	}

}
