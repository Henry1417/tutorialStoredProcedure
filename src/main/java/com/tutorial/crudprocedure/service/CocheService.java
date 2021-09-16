package com.tutorial.crudprocedure.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.crudprocedure.entity.Coche;
import com.tutorial.crudprocedure.repository.CocheRepository;

@Service
public class CocheService {

    @Autowired
    CocheRepository cocheRepository;
    String message;

    public List<Coche> getAll(){
        return cocheRepository.findAll();
    }

    public Optional<Coche> getById(int id){
        return cocheRepository.spFindById(id);
    }

    public List<Coche> getByBrand(String marca){
        return cocheRepository.findByBrand(marca);
    }

    public void saveCar(Coche car){
        cocheRepository.save(car);
    }   
    
    public String updateCar(Coche car, int id){
    	message = "Not found Car";
    	
    	cocheRepository.findById(id).ifPresent((c) -> {
    		car.setId(id);
    		cocheRepository.save(car);
    		message = "Updated Car";
    	});
    	
    	return message;
    }

    public float getAverageKm(){
        return cocheRepository.getAverageKm();
    }

    public void deleteById(int id){
        cocheRepository.deleteById(id);
    }
}
