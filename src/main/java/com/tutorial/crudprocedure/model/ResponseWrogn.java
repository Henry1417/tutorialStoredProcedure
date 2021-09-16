package com.tutorial.crudprocedure.model;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor(callSuper = false)
//@AllArgsConstructor(access = AccessLevel.NONE)
//@NoArgsConstructor(access = AccessLevel.NONE)
public class ResponseWrogn extends Response{
	String codigo;
	String info;
	HashMap<String, Object> details;	
	
	public ResponseWrogn(String message, String folio, Object result, String codigo, String info, HashMap<String, Object> details) {
		super(message, folio, result);
		this.codigo = codigo;
		this.info = info;
		this.details = details;
	}
	
}