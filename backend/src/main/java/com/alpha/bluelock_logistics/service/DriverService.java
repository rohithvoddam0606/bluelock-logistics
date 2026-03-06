package com.alpha.bluelock_logistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Carrier;
import com.alpha.bluelock_logistics.entity.Driver;
import com.alpha.bluelock_logistics.entity.Truck;
import com.alpha.bluelock_logistics.exceptions.CarrierWithIdNotFoundException;
import com.alpha.bluelock_logistics.exceptions.DriverWithIdNotFoundException;
import com.alpha.bluelock_logistics.exceptions.TruckWithIdNotFoundException;
import com.alpha.bluelock_logistics.repository.CarrierRepository;
import com.alpha.bluelock_logistics.repository.DriverRepository;
import com.alpha.bluelock_logistics.repository.TruckRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private TruckRepository truckRepository;
	@Autowired
	private CarrierRepository carrierRepository;

	ResponseStructure<Driver> rsdriver = new ResponseStructure<Driver>();

	public ResponseEntity<ResponseStructure<Driver>> saveDriver(Driver d) {
		driverRepository.save(d);
		rsdriver.setStatuscode(HttpStatus.OK.value());
		rsdriver.setMessage("Driver Saved");
		rsdriver.setData(d);
		return new ResponseEntity<ResponseStructure<Driver>>(rsdriver, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Driver>> getDriver(int id) {
		Driver d = driverRepository.findById(id)
				.orElseThrow(() -> new DriverWithIdNotFoundException("Driver with id " + id + " not found"));
		rsdriver.setStatuscode(HttpStatus.OK.value());
		rsdriver.setMessage("Driver found");
		rsdriver.setData(d);
		return new ResponseEntity<ResponseStructure<Driver>>(rsdriver, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Driver>> removeDriver(int id) {
		Driver d = driverRepository.findById(id)
				.orElseThrow(() -> new DriverWithIdNotFoundException("Driver with id " + id + " not found"));
		driverRepository.delete(d);
		rsdriver.setStatuscode(HttpStatus.OK.value());
		rsdriver.setMessage("Driver deleted");
		rsdriver.setData(d);
		return new ResponseEntity<ResponseStructure<Driver>>(rsdriver, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Driver>> modifyDriver(int driverid, int truckid, int carrierid) {
		Driver driver = driverRepository.findById(driverid)
				.orElseThrow(() -> new DriverWithIdNotFoundException("Driver with id " + driverid + " not found"));
		Truck truck = truckRepository.findById(truckid)
				.orElseThrow(() -> new TruckWithIdNotFoundException("Truck with id " + truckid + " not found"));
		Carrier carrier = carrierRepository.findById(carrierid)
				.orElseThrow(() -> new CarrierWithIdNotFoundException("Carrier with id " + carrierid + " not found"));

		driver.setTruck(truck);
		driver.setCarrier(carrier);
		driverRepository.save(driver);

		rsdriver.setStatuscode(HttpStatus.OK.value());
		rsdriver.setMessage("Driver updated");
		rsdriver.setData(driver);
		return new ResponseEntity<ResponseStructure<Driver>>(rsdriver, HttpStatus.OK);
	}

}
