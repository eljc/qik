package com.eljc.qik.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.eljc.qik.model.Dish;

public interface DishRepository extends CrudRepository<Dish, Long>{
	@Override
	List<Dish> findAll();
	
	Optional<List<Dish>> findByName(String name);

}
