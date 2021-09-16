package com.tutorial.crudprocedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response{
	
	String message;
	String folio;
	Object result;
	
}