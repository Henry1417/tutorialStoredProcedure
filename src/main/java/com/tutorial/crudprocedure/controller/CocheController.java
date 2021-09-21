package com.tutorial.crudprocedure.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.crudprocedure.entity.Coche;
import com.tutorial.crudprocedure.model.Response;
import com.tutorial.crudprocedure.model.ResponseWrogn;
import com.tutorial.crudprocedure.service.CocheService;
import com.tutorial.crudprocedure.util.ManageResponse;

@RestController
@RequestMapping("/apiTestCar/v1/")
public class CocheController {

    @Autowired
    CocheService cocheService;
    
    @Autowired
    ManageResponse manageResponse;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/cars")
    public ResponseEntity<Response> findAll(){    
    	try {
    		List<Coche> listResults = cocheService.findAll();
    		return new ResponseEntity(new Response(listResults.isEmpty() ? "No Data" : "Succes, Get Cars", "folio", listResults), HttpStatus.OK);
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Get Cars", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping("/cars/{id}")
    public ResponseEntity<Response>findById(@PathVariable("id") int id){
        try {
        	Optional<Coche> cars = cocheService.findById(id);
    		return new ResponseEntity(new Response("Succes, " + (cars.isPresent() ? " Get Cars by Id" : "Not Found"), "folio", cars), HttpStatus.OK);
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Get Cars by Id", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}        
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping(path={"/cars/brands", "/cars/brands/{brand}"})
    public ResponseEntity<ResponseEntity<List<Coche>>> findByParam(
    		@PathVariable(value="brand", required=false) String brand,
    		@RequestParam(value="model", required=false) String model){    	
    	String message="";
        try {
        	List<Coche> cars;
        	String option = brand == null ? "" : "brands";
        	option = brand != null && model!=null ? "models" : option;
        	
        	switch(option){
        		case "":        			
        			cars = cocheService.findAll();
        			return new ResponseEntity(new Response("Succes, Get Cars", "folio", cars), HttpStatus.OK);
        		case "brands":
        			message = "by Brand";
        			cars = cocheService.findByBrandsAndOrModels(1, brand, null);
        			return new ResponseEntity(new Response("Succes, Get Cars " + message, "folio", cars), HttpStatus.OK);
        		case "models":
        			message = "by Model";
        			cars = cocheService.findByBrandsAndOrModels(2, brand, model);
        			return new ResponseEntity(new Response("Succes, Get Cars " + message, "folio", cars), HttpStatus.OK);
        	}
        	
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Get Cars " + message, "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}
		return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Coche car){        
        try {        	
        	cocheService.saveCar(car);
    		return new ResponseEntity(new Response("Succes, Saved Car", "folio", null), HttpStatus.CREATED);
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Save Car", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}        
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/carsList")
    public ResponseEntity<?> addCars(@RequestBody List<Coche> coches){
    	System.out.println(coches);
        try {
        	cocheService.saveCars(coches);
    		return new ResponseEntity(new Response("Succes, Saved Cars", "folio", null), HttpStatus.CREATED);
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Save Cars", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}        
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PutMapping("/cars/{id}")
    public ResponseEntity<?> updateCar(@RequestBody Coche coche, @PathVariable("id") int id){       
        try {
        	String message = cocheService.updateCar(coche, id);
    		return new ResponseEntity(new Response("Succes, " + message, "folio", null), HttpStatus.OK);
    	}catch (Exception e) {    		
    		return new ResponseEntity(new ResponseWrogn("Error, Update Car", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}
    }   

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @DeleteMapping("cars/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        
        try {
        	cocheService.deleteById(id);
    		return new ResponseEntity(new Response("Succes, Deleted Car", "folio", null), HttpStatus.OK);
    	}catch (Exception e) {    		
    		return new ResponseEntity(new ResponseWrogn("Error, Delete Car", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}
    }
    
    // Reports
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping("/reportCars/averageKm")
    public ResponseEntity<Float> getAverageKm(){        
        try {
        	float average = cocheService.getAverageKm();
    		return new ResponseEntity(new Response("Succes, Get Average Km", "folio", average), HttpStatus.OK);
    	}catch (Exception e) {    		
    		return new ResponseEntity(new ResponseWrogn("Error, Get Average km", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}        
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/reportCars")
    public ResponseEntity<?> getReport(
    		@RequestParam(value="type", required=false) Integer type,
    		@RequestParam(value="brand", required=false) String brand,
    		@RequestParam(value="model", required=false) String model){
    	String message;
    	try {
    		List<?> report;
    		int optionType = (type == null ? 0 : type.intValue());
        	
        	switch(optionType){
        		case 1:
        			message = "Brands";
        			report = cocheService.spGetReports(type, null);
        			return new ResponseEntity(new Response(report.isEmpty() ? 
        					"No Data" : "Succes, Get Cars Report of " + message, "folio", report), HttpStatus.OK);
        		case 2:
        			message = "Brands Filter By '" + brand + "' brand";
        			
        			if(brand == null)
        				return new ResponseEntity(new Response("Warning, Get Cars Report, " +
        						"Invalid Parameters (brand=" +brand+"), No Data Found", "folio", null), HttpStatus.OK);
        			
        			report = cocheService.spGetReports(type, brand);
        			return new ResponseEntity(new Response("Succes, Get Cars Report of " + message, "folio", report), HttpStatus.OK);
        		case 3:
        			message = "Brand Models";
        			report = cocheService.spGetReports(type, brand, model);
        			return new ResponseEntity(new Response("Succes, Get Cars Report of " + message, "folio", report), HttpStatus.OK);
        		case 4:
        			message = "Brand Models Filter By '" + model + "' model";
        			
        			if(brand == null || model == null)
        				return new ResponseEntity(new Response("Warning, Get Cars Report, " +
        						"Invalid Parameters("+ (brand==null ? "brand="+brand : "") + (brand==null && model==null ? ", " : "") + 
        						(model == null ? "model="+model :"" )+"), " +
        						"No Data Found", "folio", null), HttpStatus.OK);
        			
        			report = cocheService.spGetReports(type, brand, model);
        			return new ResponseEntity(new Response("Succes, Get Cars Report of " + message, "folio", report), HttpStatus.OK);
        		default:
        			return new ResponseEntity(new Response("Warning, Get Cars Report, Invalid Parameters(type="+type+"), No Data Found", "folio", null), HttpStatus.OK);
        			
        	}
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Cars Reports", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}
    }
    
	@GetMapping("/reportCars/testMapParams")
    public ResponseEntity<?> tesMapParams(@RequestParam(required=false) Map<String,Object> qparams){    
    	
    	qparams.forEach((key, value) -> System.out.println(key + ": " + value));
    	
    	// Streams
    	System.out.println("");
    	qparams.entrySet()
		.stream()
		.forEach(System.out::println);
    	
    	System.out.println("");
    	for (Map.Entry<String, Object> entry : qparams.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue());
		}
    	
    	System.out.println("");
    	for (String key : qparams.keySet()) {
		     System.out.println(key +" : "+qparams.get(key));
		}
    	
    	return null;    	
    }
}