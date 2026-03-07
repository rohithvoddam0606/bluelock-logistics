package com.alpha.bluelock_logistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Address;
import com.alpha.bluelock_logistics.entity.Carrier;
import com.alpha.bluelock_logistics.entity.Driver;
import com.alpha.bluelock_logistics.entity.Orders;
import com.alpha.bluelock_logistics.entity.Truck;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DriverWithIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<Driver>> handleDriverWithIdNotFoundException() {
		ResponseStructure<Driver> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Driver not found");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<Driver>>(rs, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TruckWithIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<Truck>> handleTruckWithIdNotFoundException() {
		ResponseStructure<Truck> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Truck not found");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<Truck>>(rs, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CarrierWithIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<Carrier>> handlCarrierWithIdNotFoundException() {
		ResponseStructure<Carrier> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Carrier not found");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<Carrier>>(rs, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AddressWithIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<Address>> handleAddressWithIdNotFoundException() {
		ResponseStructure<Address> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Address not found");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<Address>>(rs, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OrderWithIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<Orders>> handleOrderWithIdNotFoundException() {
		ResponseStructure<Orders> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Order not found");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<Orders>>(rs, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TruckCapacityFullException.class)
	public ResponseEntity<ResponseStructure<Truck>> handleTruckCapacityFullException() {
		ResponseStructure<Truck> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.INSUFFICIENT_STORAGE.value());
		rs.setMessage("Truck Capacity full");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<Truck>>(rs, HttpStatus.INSUFFICIENT_STORAGE);
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateEmailException(DuplicateEmailException ex) {
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage(ex.getMessage());
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
	}
}
