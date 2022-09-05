package com.eljc.qik.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DishDTO {

	private Long idDish;
	
	private String name;
	
	private BigDecimal value;
}
