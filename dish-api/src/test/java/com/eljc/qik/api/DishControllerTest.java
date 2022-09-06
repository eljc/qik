package com.eljc.qik.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.eljc.qik.model.Dish;
import com.eljc.qik.model.dto.DishDTO;
import com.eljc.qik.service.DishService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
class DishControllerTest {

	@Autowired
	private DishService dishRepository;

	@DisplayName("List total itens")
	@Test
	void testListAll() {
		List<DishDTO> list = dishRepository.findAll();

		assertEquals(3, list.size());

	}

	@DisplayName("Find by ID")
	@Test
	void testFindByIdLong() {
		Optional<Dish> optional = dishRepository.findByID(3l);

		if (optional.isPresent()) {
			DishDTO dish = new DishDTO(optional.get());
			assertEquals("Pizza", dish.getName());
		}

	}

	@DisplayName("Find by Name")
	@Test
	void testFindByIdString() {
		List<DishDTO> list = dishRepository.findByName("Pizza");
		assertEquals(3l, list.get(0).getId(), "Return Id 3");
	}

	@DisplayName("Remove dish Exception")
	@Test
	void testRemoveDishExpectedException() {

		EmptyResultDataAccessException thrown = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			dishRepository.deleteByID(5l);
		});

		assertEquals("No class com.eljc.qik.model.Dish entity with id 5 exists!", thrown.getMessage());

	}

	@DisplayName("Save dish and remove")
	@Test
	void testSaveAndRemove() {

		Dish dish = new Dish();
		dish.setName("Lobster");
		dish.setValue(new BigDecimal("30.15"));
		dishRepository.save(dish);
		
		dishRepository.deleteByID(4l);

	}

}
