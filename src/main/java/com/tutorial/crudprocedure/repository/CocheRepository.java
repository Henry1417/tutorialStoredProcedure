package com.tutorial.crudprocedure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tutorial.crudprocedure.entity.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer> {

    @Query(value = "{call lista_procedure()}", nativeQuery = true)
    List<Coche> findAll();

    // When the query no return data, List return a empty list []
//    @Query(value = "{call id_procedure(:idIn)}", nativeQuery = true)
//    List<Coche> spFindById(@Param("idIn") int idIn);
    
    // When the query no return data, Otional return null
    @Query(value = "{call id_procedure(:idIn)}", nativeQuery = true)
    Optional<Coche> spFindById(@Param("idIn") int idIn);

    @Query(value = "{call marca_procedure(:marcaIn)}", nativeQuery = true)
    List<Coche> findByBrand(@Param("marcaIn") String marcaIn);    

    @Query(value = "{call save_procedure(:marcaIn, :modeloIn, :anyoIn, :kmIn)}", nativeQuery = true)
    void spSave(
            @Param("marcaIn")String marcaIn,
            @Param("modeloIn")String modeloIn,
            @Param("anyoIn")int anyoIn,
            @Param("kmIn")int kmIn
    );

    @Query(value = "{call media_procedure()}", nativeQuery = true)
    float getAverageKm();

    @Query(value = "{call borrar_procedure(:idIn)}", nativeQuery = true)
    void deleteById(@Param("idIn") int idIN);
}
