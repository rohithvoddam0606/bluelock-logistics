package com.alpha.bluelock_logistics.service;

import java.util.List;

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

	public ResponseEntity<ResponseStructure<List<Cargo>>> getAllCargo() {
		List<Cargo> allCargo = cargoRepository.findAll();
		ResponseStructure<List<Cargo>> rs = new ResponseStructure<List<Cargo>>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("All cargo retrieved");
		rs.setData(allCargo);
		return new ResponseEntity<ResponseStructure<List<Cargo>>>(rs, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Cargo>> getCargo(int id) {
		Cargo cargo = cargoRepository.findById(id).orElse(null);
		if (cargo != null) {
			rscargo.setStatuscode(HttpStatus.OK.value());
			rscargo.setMessage("Cargo found");
			rscargo.setData(cargo);
			return new ResponseEntity<ResponseStructure<Cargo>>(rscargo, HttpStatus.OK);
		} else {
			rscargo.setStatuscode(HttpStatus.NOT_FOUND.value());
			rscargo.setMessage("Cargo not found");
			return new ResponseEntity<ResponseStructure<Cargo>>(rscargo, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Cargo>> saveCargo(Cargo cargo) {
		cargoRepository.save(cargo);
		
		rscargo.setStatuscode(HttpStatus.OK.value());
		rscargo.setMessage("Cargo saved");
		rscargo.setData(cargo);
		
		return new ResponseEntity<ResponseStructure<Cargo>>(rscargo, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Cargo>> removeCargo(int id) {
		Cargo cargo = cargoRepository.findById(id).orElse(null);
		if (cargo != null) {
			cargoRepository.delete(cargo);
			rscargo.setStatuscode(HttpStatus.OK.value());
			rscargo.setMessage("Cargo deleted");
			rscargo.setData(cargo);
			return new ResponseEntity<ResponseStructure<Cargo>>(rscargo, HttpStatus.OK);
		} else {
			rscargo.setStatuscode(HttpStatus.NOT_FOUND.value());
			rscargo.setMessage("Cargo not found");
			return new ResponseEntity<ResponseStructure<Cargo>>(rscargo, HttpStatus.NOT_FOUND);
		}
	}

}
