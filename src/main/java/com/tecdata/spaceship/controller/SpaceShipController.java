package com.tecdata.spaceship.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecdata.spaceship.exception.NotValidValueException;
import com.tecdata.spaceship.model.Ship;
import com.tecdata.spaceship.services.ShipService;

@RestController
@RequestMapping("/spaceships")
public class SpaceShipController {
	
	@Autowired
	private ShipService shipService;
	
	@GetMapping("/getships")
	public ResponseEntity<Map<String,Object>> getAll(@RequestParam(required=false) String name,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id,name") String[] sort) throws Exception{
		
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
		Map<String,Object> response = new HashMap<>();
		try {
			response.put("data",(name==null) ?
					shipService.getAllShip(pageRequest) : shipService.findByName(name, pageRequest));
		}catch(DataAccessException e){
			response.put("ERROR", e);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getship/{id}")
	public ResponseEntity<Map<String,Object>> getShip(@PathVariable("id") int id) {
		
		Map<String,Object> response = new HashMap<>();
		try {
			Ship ship = shipService.getShip(id);
			response.put("data",(ship!=null)?ship:"No existe la nave con la id "+id);
		}catch(NotValidValueException nvv){
			response.put("ERROR", nvv);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}catch(DataAccessException e){
			response.put("ERROR", e);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/saveship")
	public ResponseEntity<Map<String,Object>> saveShip(@RequestBody Ship ship){
		
		Map<String,Object> response = new HashMap<>();
		try {
			System.out.println("entró");
			shipService.saveShip(ship);
			response.put("message", "La nave se agregó correctamente");
		}catch(DataAccessException e){
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateship")
	public ResponseEntity<Map<String,Object>> updateShip(@RequestBody Ship ship){
		
		Map<String,Object> response = new HashMap<>();
		try {
			shipService.saveShip(ship);
			response.put("message", "La nave se actualizó correctamente");
		}catch(DataAccessException e){
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteship")
	public ResponseEntity<Map<String,Object>> deleteShip(@RequestParam int id){
		
		Map<String,Object> response = new HashMap<>();
		try {
			shipService.deleteShip(id);
			response.put("message", "La nave se borró correctamente");
		}catch(DataAccessException e){
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
