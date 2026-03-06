package com.alpha.bluelock_logistics.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {

	@Id
	private int id;
	private LocalDate orderdate;
	private String status;
	private double cost;

	@ManyToOne
	private Carrier carrier;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cargo cargo;
	@ManyToOne
	private Loading loading;
	@ManyToOne
	private Unloading unloading;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Loading getLoading() {
		return loading;
	}

	public void setLoading(Loading loading) {
		this.loading = loading;
	}

	public Unloading getUnloading() {
		return unloading;
	}

	public void setUnloading(Unloading unloading) {
		this.unloading = unloading;
	}

	public Orders(int id, LocalDate orderdate, String status, double cost, Carrier carrier, Cargo cargo, Loading loading,
			Unloading unloading) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.status = status;
		this.cost = cost;
		this.carrier = carrier;
		this.cargo = cargo;
		this.loading = loading;
		this.unloading = unloading;
	}

	public Orders() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderdate=" + orderdate + ", status=" + status + ", cost=" + cost + ", carrier="
				+ carrier + ", cargo=" + cargo + ", loading=" + loading + ", unloading=" + unloading + "]";
	}

}
