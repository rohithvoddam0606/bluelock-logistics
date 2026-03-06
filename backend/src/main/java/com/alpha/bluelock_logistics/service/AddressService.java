package com.alpha.bluelock_logistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Address;
import com.alpha.bluelock_logistics.exceptions.AddressWithIdNotFoundException;
import com.alpha.bluelock_logistics.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	ResponseStructure<Address> rsaddress = new ResponseStructure<Address>();

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		addressRepository.save(address);
		rsaddress.setStatuscode(HttpStatus.OK.value());
		rsaddress.setMessage("Address Saved");
		rsaddress.setData(address);

		return new ResponseEntity<ResponseStructure<Address>>(rsaddress, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Address>> getAddress(int id) {
		Address a = addressRepository.findById(id)
				.orElseThrow(() -> new AddressWithIdNotFoundException("Address with id " + id + " not found"));
		rsaddress.setStatuscode(HttpStatus.OK.value());
		rsaddress.setMessage("Address found");
		rsaddress.setData(a);

		return new ResponseEntity<ResponseStructure<Address>>(rsaddress, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Address>> removeAddress(int id) {
		Address a = addressRepository.findById(id)
				.orElseThrow(() -> new AddressWithIdNotFoundException("Address with id " + id + " not found"));
		addressRepository.delete(a);
		rsaddress.setStatuscode(HttpStatus.OK.value());
		rsaddress.setMessage("Address deleted");
		rsaddress.setData(a);

		return new ResponseEntity<ResponseStructure<Address>>(rsaddress, HttpStatus.OK);
	}

}
