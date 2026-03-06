package com.alpha.bluelock_logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Carrier;
import com.alpha.bluelock_logistics.exceptions.CarrierWithIdNotFoundException;
import com.alpha.bluelock_logistics.repository.CarrierRepository;

@Service
public class CarrierService {
	@Autowired
	private CarrierRepository carrierRepository;

	ResponseStructure<Carrier> rscarrier = new ResponseStructure<Carrier>();

	public ResponseEntity<ResponseStructure<List<Carrier>>> getAllCarriers() {
		List<Carrier> allCarriers = carrierRepository.findAll();
		ResponseStructure<List<Carrier>> rs = new ResponseStructure<List<Carrier>>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("All carriers retrieved");
		rs.setData(allCarriers);
		return new ResponseEntity<ResponseStructure<List<Carrier>>>(rs, HttpStatus.OK);
	}

	// ADDING CARRIER
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(Carrier c) {
		carrierRepository.save(c);
		rscarrier.setStatuscode(HttpStatus.OK.value());
		rscarrier.setMessage("Carrier Saved");
		rscarrier.setData(c);
		return new ResponseEntity<ResponseStructure<Carrier>>(rscarrier, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Carrier>> getCarrier(int id) {
		Carrier c = carrierRepository.findById(id)
				.orElseThrow(() -> new CarrierWithIdNotFoundException("Carrier with id " + id + " not found"));
		rscarrier.setStatuscode(HttpStatus.OK.value());
		rscarrier.setMessage("Carrier found");
		rscarrier.setData(c);
		return new ResponseEntity<ResponseStructure<Carrier>>(rscarrier, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Carrier>> removeCarrier(int id) {
		Carrier c = carrierRepository.findById(id)
				.orElseThrow(() -> new CarrierWithIdNotFoundException("Carrier with id " + id + " not found"));
		carrierRepository.delete(c);
		rscarrier.setStatuscode(HttpStatus.OK.value());
		rscarrier.setMessage("Carrier deleted");
		rscarrier.setData(c);
		return new ResponseEntity<ResponseStructure<Carrier>>(rscarrier, HttpStatus.OK);
	}

}
