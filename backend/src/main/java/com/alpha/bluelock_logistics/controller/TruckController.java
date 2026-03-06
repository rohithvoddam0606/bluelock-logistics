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
import com.alpha.bluelock_logistics.entity.Truck;
import com.alpha.bluelock_logistics.service.TruckService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TruckController {

	@Autowired
	private TruckService truckService;

	@GetMapping("/trucks")
	public ResponseEntity<ResponseStructure<List<Truck>>> getAllTrucks() {
		return truckService.getAllTrucks();
	}

	@GetMapping("/trucks/{id}")
	public ResponseEntity<ResponseStructure<Truck>> getTruckById(@PathVariable int id) {
		return truckService.getTruck(id);
	}

	@PostMapping("/trucks")
	public ResponseEntity<ResponseStructure<Truck>> createTruck(@RequestBody Truck t) {
		return truckService.saveTruck(t);
	}

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
