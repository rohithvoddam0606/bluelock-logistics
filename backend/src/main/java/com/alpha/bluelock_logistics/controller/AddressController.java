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
import com.alpha.bluelock_logistics.entity.Address;
import com.alpha.bluelock_logistics.service.AddressService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {
	@Autowired
	private AddressService addressService;

	@GetMapping("/addresses")
	public ResponseEntity<ResponseStructure<List<Address>>> getAllAddresses() {
		return addressService.getAllAddresses();
	}

	@GetMapping("/addresses/{id}")
	public ResponseEntity<ResponseStructure<Address>> getAddressById(@PathVariable int id) {
		return addressService.getAddress(id);
	}

	@PostMapping("/addresses")
	public ResponseEntity<ResponseStructure<Address>> createAddress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}

	@PutMapping("/addresses/{id}")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@PathVariable int id, @RequestBody Address address) {
		address.setId(id);
		return addressService.saveAddress(address);
	}

	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@PathVariable int id) {
		return addressService.removeAddress(id);
	}

	@PostMapping("/addaddress")
	public ResponseEntity<ResponseStructure<Address>> addAddress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}

	@GetMapping("/findaddress/{id}")
	public ResponseEntity<ResponseStructure<Address>> findAddress(@PathVariable int id) {
		return addressService.getAddress(id);
	}

	@DeleteMapping("/deleteaddress/{id}")
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(@PathVariable int id) {
		return addressService.removeAddress(id);
	}

}
