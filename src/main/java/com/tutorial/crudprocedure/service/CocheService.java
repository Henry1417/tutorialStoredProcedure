package com.tutorial.crudprocedure.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.crudprocedure.entity.Coche;
import com.tutorial.crudprocedure.model.CarsBrands;
import com.tutorial.crudprocedure.model.CarsModels;
import com.tutorial.crudprocedure.repository.CarsReportsRepository;
import com.tutorial.crudprocedure.repository.CocheRepository;

@Service
public class CocheService {

    @Autowired
    CocheRepository cocheRepository;
    
    @Autowired
    CarsReportsRepository carsReportsRepository;
    
    private String message;

    public List<Coche> findAll(){
        return cocheRepository.spFindAll();
    }

    public Optional<Coche> findById(int id){
        return cocheRepository.spFindById(id);
    }

    public List<Coche> findByBrandsAndOrModels(int type, String brand, String model){
        return cocheRepository.spFindByBrandsAndOrModels(type, brand, model);
    }

    public void saveCar(Coche car){
        cocheRepository.spSave(car.getMarca(), car.getModelo(), car.getAnyo(), car.getKm());
    }
    
    public void saveCars(List<Coche> cars){
        cocheRepository.saveAll(cars);
    }   
    
    public String updateCar(Coche car, int id){
    	message = "Not Found Car";
    	
    	cocheRepository.findById(id).ifPresent((c) -> {
    		car.setId(id);
    		cocheRepository.save(car);
    		message = "Updated Car";
    	});
    	
    	return message;
    }

    public float getAverageKm(){
        return cocheRepository.spGetAverageKm();
    }

    public void deleteById(int id){
        cocheRepository.spDeleteById(id);
    }
    
    //Reports
    public List<CarsBrands> getReports(int type, String brand){        		
    	return carsReportsRepository.getReports(type, brand);	
    }
    
    public List<CarsModels> getReports(int type, String brand, String model){
    	return carsReportsRepository.getReports(type, brand, model);
    }
    
    public List<CarsBrands> spGetReports(int type, String brand){        		
    	return carsReportsRepository.spGetReports(type, brand);	
    }
    
    public List<CarsModels> spGetReports(int type, String brand, String model){        		
    	return carsReportsRepository.spGetReports(type, brand, model);	
    }
}
