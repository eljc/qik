package com.eljc.qik.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.eljc.qik.model.Dish;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DishDTO {
	
    private Long id;

    @NotNull(message = "Dish name cannot be null")
    private String name;

    @Min(value = 5, message = "The value should not be less than 5")
    @Max(value = 100, message = "The value should not be greater than 100")
    private BigDecimal value;

    
    public Dish toEntity() {
    	Dish dish = new Dish();
    	dish.setName(this.getName());
    	dish.setValue(this.getValue());
    	
    	return dish;
    }
    
    public DishDTO(Dish dish) {
    	this.id = dish.getId();
    	this.name = dish.getName();
    	this.value = dish.getValue();
    }
}
