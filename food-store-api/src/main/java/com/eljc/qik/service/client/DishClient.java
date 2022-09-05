package com.eljc.qik.service.client;

import java.util.List;

import com.eljc.qik.model.dto.DishDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("dish-api")
public interface DishClient{

	@RequestMapping("/listAll")
	List<DishDTO> getAllDishes();
}
