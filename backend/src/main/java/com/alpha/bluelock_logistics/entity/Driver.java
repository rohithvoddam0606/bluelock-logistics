package com.alpha.bluelock_logistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Driver {

	@Id
	private int id;
	private String name;
	private long contact;
	@ManyToOne
	private Truck truck;
	@ManyToOne
	private Carrier carrier;

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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public Driver(int id, String name, long contact, Carrier carrier, Truck truck) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.carrier = carrier;
		this.truck = truck;
	}

	public Driver() {
		super();
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", contact=" + contact + ", carrier=" + carrier + ", truck="
				+ truck + "]";
	}

}
