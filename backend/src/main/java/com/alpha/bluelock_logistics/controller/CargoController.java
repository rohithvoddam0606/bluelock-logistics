package com.alpha.bluelock_logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Cargo;
import com.alpha.bluelock_logistics.service.CargoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;

	@GetMapping("/cargo")
	public ResponseEntity<ResponseStructure<List<Cargo>>> getAllCargo() {
		return cargoService.getAllCargo();
	}

	@GetMapping("/cargo/{id}")
	public ResponseEntity<ResponseStructure<Cargo>> getCargoById(@PathVariable int id) {
		return cargoService.getCargo(id);
	}

	@PostMapping("/cargo")
	public ResponseEntity<ResponseStructure<Cargo>> createCargo(@RequestBody Cargo cargo) {
		return cargoService.saveCargo(cargo);
	}

	@PutMapping("/cargo/{id}")
	public ResponseEntity<ResponseStructure<Cargo>> updateCargo(@PathVariable int id, @RequestBody Cargo cargo) {
		cargo.setId(id);
		return cargoService.saveCargo(cargo);
	}

	@DeleteMapping("/cargo/{id}")
	public ResponseEntity<ResponseStructure<Cargo>> deleteCargo(@PathVariable int id) {
		return cargoService.removeCargo(id);
	}
	
	@PostMapping("/addcargo")
	public ResponseEntity<ResponseStructure<Cargo>> addCargo(@RequestBody Cargo cargo) {
		return cargoService.saveCargo(cargo);
	}

}
