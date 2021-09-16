package com.tutorial.crudprocedure.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.crudprocedure.entity.Coche;
import com.tutorial.crudprocedure.model.Response;
import com.tutorial.crudprocedure.model.ResponseWrogn;
import com.tutorial.crudprocedure.service.CocheService;
import com.tutorial.crudprocedure.util.ManageResponse;

@RestController
//@RequestMapping("/cars")
public class CocheController {

    @Autowired
    CocheService cocheService;
    
    @Autowired
    ManageResponse manageResponse;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/cars")
    public ResponseEntity<Response> findAll(){
    	try {    		
    		List<Coche> listResults = cocheService.getAll();
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
    public ResponseEntity<Coche>findById(@PathVariable("id") int id){
        try {
        	Optional<Coche> cars = cocheService.getById(id);
    		return new ResponseEntity(new Response("Succes, " + (cars.isPresent() ? " Get Cars by Id" : "Not Found"), "folio", cars), HttpStatus.OK);
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Get Cars by Id", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}        
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping("cars/brands/{brand}")
    public ResponseEntity<List<Coche>> findByBrand(@PathVariable("brand") String brand){        
        try {
        	List<Coche> cars = cocheService.getByBrand(brand);
    		return new ResponseEntity(new Response("Succes, Get Cars by Brand", "folio", cars), HttpStatus.OK);
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Get Cars by Brands", "folio", null,
    				HttpStatus.EXPECTATION_FAILED.value() + ".cars.[codigo back]", "", 
    				manageResponse.getDetailsResponse(e)), 
    				HttpStatus.EXPECTATION_FAILED);
		}        
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Coche coche){        
        try {
        	cocheService.saveCar(coche);
    		return new ResponseEntity(new Response("Succes, Saved Car", "folio", null), HttpStatus.CREATED);
    	}catch (Exception e) {
    		
    		return new ResponseEntity(new ResponseWrogn("Error, Save Car", "folio", null,
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
    @GetMapping("/cars/averageKm")
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
}