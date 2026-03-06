package com.alpha.bluelock_logistics.dto;

public class OrderDto {
	private int id;
//	private LocalDate orderDate;
	private int cargoId;
	private String cargoName;
	private String cargoDescription;
	private int cargoWeight;
	private int cargoCount;
	private int loadingAddressId;
	private int unloadingAddressId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public LocalDate getOrderDate() {
//		return orderDate;
//	}
//
//	public void setOrderDate(LocalDate orderDate) {
//		this.orderDate = orderDate;
//	}

	public int getCargoId() {
		return cargoId;
	}

	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoDescription() {
		return cargoDescription;
	}

	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}

	public int getCargoWeight() {
		return cargoWeight;
	}

	public void setCargoWeight(int cargoWeight) {
		this.cargoWeight = cargoWeight;
	}

	public int getCargoCount() {
		return cargoCount;
	}

	public void setCargoCount(int cargoCount) {
		this.cargoCount = cargoCount;
	}

	public int getLoadingAddressId() {
		return loadingAddressId;
	}

	public void setLoadingAddressId(int loadingAddressId) {
		this.loadingAddressId = loadingAddressId;
	}

	public int getUnloadingAddressId() {
		return unloadingAddressId;
	}

	public void setUnloadingAddressId(int unloadingAddressId) {
		this.unloadingAddressId = unloadingAddressId;
	}

	public OrderDto(int id, int cargoId, String cargoName, String cargoDescription, int cargoWeight,
			int cargoCount, int loadingAddressId, int unloadingAddressId) {
		super();
		this.id = id;
//		this.orderDate = orderDate;
		this.cargoId = cargoId;
		this.cargoName = cargoName;
		this.cargoDescription = cargoDescription;
		this.cargoWeight = cargoWeight;
		this.cargoCount = cargoCount;
		this.loadingAddressId = loadingAddressId;
		this.unloadingAddressId = unloadingAddressId;
	}

	public OrderDto() {
		super();
	}

}
