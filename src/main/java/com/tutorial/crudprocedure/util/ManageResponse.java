package com.tutorial.crudprocedure.util;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class ManageResponse{
	
	public HashMap<String, Object> getDetailsResponse(Exception e){
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put("emessage", e.getMessage());
		details.put("eLocalizedMessage", e.getLocalizedMessage());
		details.put("eStackTrace", e.getStackTrace());
		
		return details;
	}
	
}