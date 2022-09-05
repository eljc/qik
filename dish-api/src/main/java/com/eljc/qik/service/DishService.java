package com.eljc.qik.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eljc.qik.model.Dish;
import com.eljc.qik.model.dto.DishDTO;
import com.eljc.qik.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;

	public List<DishDTO> findAll() {
		return dishRepository.findAll().stream().map(DishDTO::new).collect(Collectors.toCollection(ArrayList::new));
	}

	public Optional<Dish> findByID(Long id) {
		Optional<Dish> dish = dishRepository.findById(id);
		if(dish.isPresent()) {
			return Optional.of(dish.get());
		}
		return Optional.empty();
	}

	public List<DishDTO> findByName(String name) {

		List<Dish> listDish = new ArrayList<>();

		Optional<List<Dish>> optDish = dishRepository.findByName(name);
		if (optDish.isPresent())
			listDish.addAll(optDish.get());

		return listDish.stream().map(DishDTO::new).collect(Collectors.toCollection(ArrayList::new));

	}
	
	public DishDTO save(Dish dish) {
		Dish disSave = dishRepository.save(dish);
		DishDTO dishDTO = new DishDTO(disSave);
		
		return dishDTO;		
	}
	
	public void deleteByID(Long id) {
		dishRepository.deleteById(id);		
	}
}
