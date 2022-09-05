package com.eljc.qik.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eljc.qik.model.Dish;
import com.eljc.qik.model.dto.DishDTO;
import com.eljc.qik.service.DishService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DishController {
	protected Logger logger = Logger.getLogger(DishController.class.getName());

	@Autowired
    private DishService dishService;
	
	@GetMapping("/listAll")
    @ResponseBody
    @Operation(summary = "List all Dishs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "List of Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
	@CrossOrigin(origins = "http://localhost:8060")
    public List<DishDTO> listAll() {
        return dishService.findAll();

    }


    @PostMapping("/save")
    @Operation(summary = "Save a new Dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public DishDTO authenticate(@RequestBody DishDTO dto) {
        Dish produc = dto.toEntity();

        return dto;
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    @Operation(summary = "find a specific")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public DishDTO findById(@RequestParam(required = true) Long idDish) {
        return dishService.findByID(idDish);

    }
    
    @GetMapping("/findByName/{name}")
    @ResponseBody
    @Operation(summary = "find a specific dish for name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public List<DishDTO> findById(@RequestParam(required = true) String name) {
    	
        return dishService.findByName(name);
        
    }
}
