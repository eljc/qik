package com.eljc.qik.api;

import java.util.List;
import java.util.logging.Logger;

import com.eljc.qik.model.dto.DishDTO;
import com.eljc.qik.service.client.DishClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://localhost:8060")
@RestController("/store")
public class FooStoreController {
	
	protected Logger logger = Logger.getLogger(FooStoreController.class.getName());

	@Autowired
	private DishClient dishClient;
	
	@GetMapping("/listAll")
    @ResponseBody
    @Operation(summary = "List all Dishs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })	
    public List<DishDTO> listAll() {
		
		
		return dishClient.getAllDishes();
        //return dishService.listAll();

    }

}
