package com.alpha.bluelock_logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Driver;
import com.alpha.bluelock_logistics.service.DriverService;

@RestController
public class DriverController {

	@Autowired
	private DriverService driverService;

	@PostMapping("/adddriver")
	public ResponseEntity<ResponseStructure<Driver>> addDriver(@RequestBody Driver d) {
		return driverService.saveDriver(d);
	}

	@GetMapping("/finddriver/{id}")
	public ResponseEntity<ResponseStructure<Driver>> findDriver(@PathVariable int id) {
		return driverService.getDriver(id);
	}
	
	@DeleteMapping("/deletedriver/{id}")
	public ResponseEntity<ResponseStructure<Driver>> deleteDriver(@PathVariable int id) {
		return driverService.removeDriver(id);
	}
	
	@PutMapping("/updatedriver/{driverid}/assigntruck/{truckid}/assigncarrier/{carrierid}")
	public ResponseEntity<ResponseStructure<Driver>> updateDriver(@PathVariable int driverid, @PathVariable int truckid, @PathVariable int carrierid) {
		return driverService.modifyDriver(driverid, truckid, carrierid);
	}
}
