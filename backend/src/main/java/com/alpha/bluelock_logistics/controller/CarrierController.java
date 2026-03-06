package com.alpha.bluelock_logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Carrier;
import com.alpha.bluelock_logistics.service.CarrierService;

@RestController
public class CarrierController {

	@Autowired
	private CarrierService carrierService;

	@PostMapping("/addcarrier")
	public ResponseEntity<ResponseStructure<Carrier>> addCarrier(@RequestBody Carrier c) {
		return carrierService.saveCarrier(c);
	}
	
	@GetMapping("/findcarrier/{id}")
	public ResponseEntity<ResponseStructure<Carrier>> findCarrier(@PathVariable int id) {
		return carrierService.getCarrier(id);
	}
	
	@DeleteMapping("/deletecarrier/{id}")
	public ResponseEntity<ResponseStructure<Carrier>> deleteCarrier(@PathVariable int id) {
		return carrierService.removeCarrier(id);
	}
}
