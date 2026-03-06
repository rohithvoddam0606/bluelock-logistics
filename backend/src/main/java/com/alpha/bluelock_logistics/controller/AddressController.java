package com.alpha.bluelock_logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Address;
import com.alpha.bluelock_logistics.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;

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
