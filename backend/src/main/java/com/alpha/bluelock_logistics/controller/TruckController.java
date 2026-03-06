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
import com.alpha.bluelock_logistics.entity.Truck;
import com.alpha.bluelock_logistics.service.TruckService;

@RestController
public class TruckController {

	@Autowired
	private TruckService truckService;

	@PostMapping("/addtruck")
	public ResponseEntity<ResponseStructure<Truck>> addTruck(@RequestBody Truck t) {
		return truckService.saveTruck(t);
	}

	@GetMapping("/findtruck/{id}")
	public ResponseEntity<ResponseStructure<Truck>> findTruck(@PathVariable int id) {
		return truckService.getTruck(id);
	}
	
	@DeleteMapping("/deletetruck/{id}")
	public ResponseEntity<ResponseStructure<Truck>> deleteTruck(@PathVariable int id) {
		return truckService.removeTruck(id);
	}
	
	@PutMapping("/updatetruck/{truckid}/assigncarrier/{carrierid}")
	public ResponseEntity<ResponseStructure<Truck>> updateTruck(@PathVariable int truckid, @PathVariable int carrierid) {
		return truckService.modifyTruck(truckid, carrierid);
	}
}
