package com.eljc.qik.api;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.eljc.qik.model.Dish;
import com.eljc.qik.model.dto.DishDTO;
import com.eljc.qik.service.DishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@CrossOrigin(origins = "http://localhost:8060")
@RestController
public class DishController {
	protected Logger logger = Logger.getLogger(DishController.class.getName());

	@Autowired
    private DishService dishService;
	
	@GetMapping("/listAll")
    @ResponseBody
    @Operation(summary = "List all Dishs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })	
    public List<DishDTO> listAll() {
        return dishService.findAll();

    }


    @PostMapping("/save")
    @Operation(summary = "Save a new Dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public DishDTO authenticate(@RequestBody DishDTO dishDto) {
        Dish dish = dishDto.toEntity();

        DishDTO dto = dishService.save(dish);
        
        return dto;
    }

    @GetMapping("/findById/{idDish}")
    @ResponseBody
    @Operation(summary = "find a specific")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return Dish"),
            @ApiResponse(responseCode = "404", description = "ID not found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public ResponseEntity<DishDTO> findById(@RequestParam(required = true) Long idDish) {
    	Optional<Dish> optional =  dishService.findByID(idDish);
    	if(optional.isPresent()) {
    		return ResponseEntity.ok(new DishDTO(optional.get()));
    	}
    	return ResponseEntity.notFound().build();

    }
    
    @GetMapping("/findByName/{name}")
    @ResponseBody
    @Operation(summary = "find a specific dish for name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return Dish"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public List<DishDTO> findById(@RequestParam(required = true) String name) {
    	
        return dishService.findByName(name);
        
    }
    
    @DeleteMapping("/remove/{idDish}")
    @ResponseBody
    @Operation(summary = "Remove an iten")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return Dish remove"),
            @ApiResponse(responseCode = "404", description = "ID not found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public  ResponseEntity<HttpStatus> removeDish(@RequestParam(required = true) Long idDish) {
    	try {
    		dishService.deleteByID(idDish);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        
    }
}
