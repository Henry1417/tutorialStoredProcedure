package com.tutorial.crudprocedure.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tutorial.crudprocedure.model.CarsBrands;
import com.tutorial.crudprocedure.model.CarsModels;

@Repository
public class CarsReportsRepository{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcCall simpleJdbcCall;
	
	// init SimpleJdbcCall
    @PostConstruct
    void init() {

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_reports");

    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CarsBrands> getReports(int type, String brand) {
		 String sql = "SELECT marca, count(1) total FROM coche group by marca";		 		 		 
			
		 if(type == 1) {
			 return jdbcTemplate.query(sql, new BeanPropertyRowMapper(CarsBrands.class));
		 }
		 
		 if(type == 2) {
			 sql = "SELECT marca, count(1) total FROM coche where marca=? group by marca";			 
			 return jdbcTemplate.query(sql, new Object[] {brand}, new BeanPropertyRowMapper(CarsBrands.class));
		 }
		 
		 return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CarsModels> getReports(int type, String brand, String model) {
		String sql = "SELECT modelo, count(1) total FROM coche where marca=? group by modelo";
		
		if(type == 3) {
			 return jdbcTemplate.query(sql, new Object[]{brand}, new BeanPropertyRowMapper(CarsModels.class));
		 }
		
		if(type == 4) {
			sql = "SELECT modelo, count(1) total FROM coche where marca=? and modelo=? group by modelo";
			 return jdbcTemplate.query(sql, new Object[]{brand, model}, new BeanPropertyRowMapper(CarsModels.class));
		 }
		return null;
	}
	 
    // Reports with SP
    public List<CarsBrands> spGetReports(int type, String brand){
    	System.out.println("get Reports with Stored Procedure");
    	SqlParameterSource in = new MapSqlParameterSource()
                .addValue("typeIn", type)
    			.addValue("brandIn", brand)
    			.addValue("modelIn", null);
    	
    	simpleJdbcCall.returningResultSet("first", new BeanPropertyRowMapper(CarsBrands.class));
//    	simpleJdbcCall.returningResultSet("second", new BeanPropertyRowMapper(CarsModels.class)); // example

        Map out = simpleJdbcCall.execute(in);
        
        return (List<CarsBrands>) out.get("first");
                                         
    }
    
    public List<CarsModels> spGetReports(int type, String brand, String model){
    	System.out.println("get Reports with Stored Procedure");
    	SqlParameterSource in = new MapSqlParameterSource()
                .addValue("typeIn", type)
    			.addValue("brandIn", brand)
    			.addValue("modelIn", model);
    	
    	simpleJdbcCall.returningResultSet("first", new BeanPropertyRowMapper(CarsModels.class));    	
        Map out = simpleJdbcCall.execute(in);              
              
        return (List<CarsModels>) out.get("first");
                                 
    }
    
}
