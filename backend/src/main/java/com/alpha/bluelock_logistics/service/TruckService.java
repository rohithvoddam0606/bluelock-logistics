package com.alpha.bluelock_logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Carrier;
import com.alpha.bluelock_logistics.entity.Truck;
import com.alpha.bluelock_logistics.exceptions.CarrierWithIdNotFoundException;
import com.alpha.bluelock_logistics.exceptions.TruckWithIdNotFoundException;
import com.alpha.bluelock_logistics.repository.CarrierRepository;
import com.alpha.bluelock_logistics.repository.TruckRepository;

@Service
public class TruckService {
	@Autowired
	private TruckRepository truckRepository;
	@Autowired
	private CarrierRepository carrierRepository;

	ResponseStructure<Truck> rstruck = new ResponseStructure<Truck>();

	public ResponseEntity<ResponseStructure<List<Truck>>> getAllTrucks() {
		List<Truck> allTrucks = truckRepository.findAll();
		ResponseStructure<List<Truck>> rs = new ResponseStructure<List<Truck>>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("All trucks retrieved");
		rs.setData(allTrucks);
		return new ResponseEntity<ResponseStructure<List<Truck>>>(rs, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Truck>> saveTruck(Truck t) {
		truckRepository.save(t);
		rstruck.setStatuscode(HttpStatus.OK.value());
		rstruck.setMessage("Truck Saved");
		rstruck.setData(t);
		return new ResponseEntity<ResponseStructure<Truck>>(rstruck, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Truck>> getTruck(int id) {
		Truck t = truckRepository.findById(id)
				.orElseThrow(() -> new TruckWithIdNotFoundException("Truck with id " + id + " not found"));
		truckRepository.delete(t);
		rstruck.setStatuscode(HttpStatus.OK.value());
		rstruck.setMessage("Truck found");
		rstruck.setData(t);
		return new ResponseEntity<ResponseStructure<Truck>>(rstruck, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Truck>> removeTruck(int id) {
		Truck t = truckRepository.findById(id)
				.orElseThrow(() -> new TruckWithIdNotFoundException("Truck with id " + id + " not found"));
		rstruck.setStatuscode(HttpStatus.OK.value());
		rstruck.setMessage("Truck deleted");
		rstruck.setData(t);
		return new ResponseEntity<ResponseStructure<Truck>>(rstruck, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Truck>> modifyTruck(int truckid, int carrierid) {
		Truck truck = truckRepository.findById(truckid)
				.orElseThrow(() -> new TruckWithIdNotFoundException("Truck with id " + truckid + " not found"));
		Carrier carrier = carrierRepository.findById(carrierid)
				.orElseThrow(() -> new CarrierWithIdNotFoundException("Carrier with id " + carrierid + " not found"));

		truck.setCarrier(carrier);
		truckRepository.save(truck);

		rstruck.setStatuscode(HttpStatus.OK.value());
		rstruck.setMessage("Truck updated");
		rstruck.setData(truck);
		return new ResponseEntity<ResponseStructure<Truck>>(rstruck, HttpStatus.OK);
	}

}
