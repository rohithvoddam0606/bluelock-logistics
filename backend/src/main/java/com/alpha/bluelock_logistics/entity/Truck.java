package com.alpha.bluelock_logistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Truck {

	@Id
	private int id;
	private String name;
	private int number;
	private int capacity;
	private String status;
	
	@ManyToOne
	private Carrier carrier;
	
	@ManyToOne
	private Address currentLocation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Address getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Address currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Truck(int id, String name, int number, int capacity, String status, Carrier carrier, Address currentLocation) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.capacity = capacity;
		this.status = status;
		this.carrier = carrier;
		this.currentLocation = currentLocation;
	}

	public Truck() {
		super();
	}

	@Override
	public String toString() {
		return "Truck [id=" + id + ", name=" + name + ", number=" + number + ", capacity=" + capacity + ", status="
				+ status + ", carrier=" + carrier + ", currentLocation=" + currentLocation + "]";
	}

}
