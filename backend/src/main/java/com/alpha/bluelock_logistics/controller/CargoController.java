package com.alpha.bluelock_logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Cargo;
import com.alpha.bluelock_logistics.service.CargoService;

@RestController
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@PostMapping("/addcargo")
	public ResponseEntity<ResponseStructure<Cargo>> addCargo(@RequestBody Cargo cargo) {
		return cargoService.saveCargo(cargo);
	}

}
