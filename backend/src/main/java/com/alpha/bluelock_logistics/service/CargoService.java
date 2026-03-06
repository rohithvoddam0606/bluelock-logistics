package com.alpha.bluelock_logistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Cargo;
import com.alpha.bluelock_logistics.repository.CargoRepository;

@Service
public class CargoService {
	@Autowired
	private CargoRepository cargoRepository;

	ResponseStructure<Cargo> rscargo = new ResponseStructure<Cargo>();
	public ResponseEntity<ResponseStructure<Cargo>> saveCargo(Cargo cargo) {
		cargoRepository.save(cargo);
		
		rscargo.setStatuscode(HttpStatus.OK.value());
		rscargo.setMessage("Cargo saved");
		rscargo.setData(cargo);
		
		return new ResponseEntity<ResponseStructure<Cargo>>(rscargo, HttpStatus.OK);
	}
	

}
