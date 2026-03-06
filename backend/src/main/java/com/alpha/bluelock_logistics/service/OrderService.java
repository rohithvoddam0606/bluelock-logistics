package com.alpha.bluelock_logistics.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.bluelock_logistics.dto.OrderDto;
import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Address;
import com.alpha.bluelock_logistics.entity.Cargo;
import com.alpha.bluelock_logistics.entity.Loading;
import com.alpha.bluelock_logistics.entity.Orders;
import com.alpha.bluelock_logistics.entity.Truck;
import com.alpha.bluelock_logistics.entity.Unloading;
import com.alpha.bluelock_logistics.exceptions.AddressWithIdNotFoundException;
import com.alpha.bluelock_logistics.exceptions.OrderWithIdNotFoundException;
import com.alpha.bluelock_logistics.exceptions.TruckCapacityFullException;
import com.alpha.bluelock_logistics.exceptions.TruckWithIdNotFoundException;
import com.alpha.bluelock_logistics.repository.AddressRepository;
import com.alpha.bluelock_logistics.repository.OrderRepository;
import com.alpha.bluelock_logistics.repository.TruckRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private TruckRepository truckRepository;

	ResponseStructure<Orders> rsorder = new ResponseStructure<Orders>();

	// SAVE OR PLACE ORDER
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(OrderDto orderDto) {

		Address loadingAddress = addressRepository.findById(orderDto.getLoadingAddressId())
				.orElseThrow(() -> new AddressWithIdNotFoundException(
						"Address with id " + orderDto.getLoadingAddressId() + " not found"));
		Address unloadingAddress = addressRepository.findById(orderDto.getUnloadingAddressId())
				.orElseThrow(() -> new AddressWithIdNotFoundException(
						"Address with id " + orderDto.getUnloadingAddressId() + " not found"));

		Orders order = new Orders();
		order.setId(orderDto.getId());
		order.setOrderdate(LocalDate.now());
		order.setStatus("Placed");

		double cost = 100 * orderDto.getCargoCount() * orderDto.getCargoWeight();
		order.setCost(cost);

		Cargo cargo = new Cargo();
		cargo.setId(orderDto.getCargoId());
		cargo.setName(orderDto.getCargoName());
		cargo.setDescription(orderDto.getCargoDescription());
		cargo.setWeight(orderDto.getCargoWeight());
		cargo.setCount(orderDto.getCargoCount());
		order.setCargo(cargo);

		Loading loading = new Loading();
		loading.setId(order.getId());
		loading.setAddress(loadingAddress);
		order.setLoading(loading);

		Unloading unloading = new Unloading();
		unloading.setId(order.getId());
		unloading.setAddress(unloadingAddress);
		order.setUnloading(unloading);
		orderRepository.save(order);

		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("Order placed successfully !");
		rsorder.setData(order);

		return new ResponseEntity<ResponseStructure<Orders>>(rsorder, HttpStatus.OK);
	}

	// TO ADD CARRIER TO ORDER USING TRUCK ID AND ORDER ID AND UPDATE ORDER
	public ResponseEntity<ResponseStructure<Orders>> modifyOrder(int orderid, int truckid) {
		Orders order = orderRepository.findById(orderid)
				.orElseThrow(() -> new OrderWithIdNotFoundException("Order with id " + orderid + " not found"));
		Truck truck = truckRepository.findById(truckid)
				.orElseThrow(() -> new TruckWithIdNotFoundException("Truck with id " + truckid + " not found"));

		int truckCapacity = truck.getCapacity();
		int orderWeight = order.getCargo().getWeight() * order.getCargo().getCount();
		if (truckCapacity >= orderWeight) {
			truck.setCapacity(truckCapacity - orderWeight);
			if (truckCapacity <= 10) {
				truck.setStatus("Full");
			} else {
				truck.setStatus("Placed");
			}
			truckRepository.save(truck);

			order.setStatus("Placed");
			order.setCarrier(truck.getCarrier());
			orderRepository.save(order);
		} else {
			throw new TruckCapacityFullException("Truck Capacity is Full");
		}
		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("Order Updated");
		rsorder.setData(order);
		return new ResponseEntity<ResponseStructure<Orders>>(rsorder, HttpStatus.OK);
	}

	// MODIFY THE LOADING DATA
	public ResponseEntity<ResponseStructure<Orders>> modifyLoading(int orderid) {
		Orders order = orderRepository.findById(orderid)
				.orElseThrow(() -> new OrderWithIdNotFoundException("Order with id " + orderid + " not found"));
		order.getLoading().setDate(LocalDate.now());
		order.getLoading().setTime(LocalTime.now());
		order.setStatus("Pending");
		orderRepository.save(order);

		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("Order Updated with loading");
		rsorder.setData(order);

		return new ResponseEntity<ResponseStructure<Orders>>(rsorder, HttpStatus.OK);
	}

	// MODIFY THE UNLOADING DATA
	public ResponseEntity<ResponseStructure<Orders>> modifyUnloading(int orderid) {
		Orders order = orderRepository.findById(orderid)
				.orElseThrow(() -> new OrderWithIdNotFoundException("Order with id " + orderid + " not found"));
		order.getUnloading().setDate(LocalDate.now());
		order.getUnloading().setTime(LocalTime.now());
		orderRepository.save(order);

		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("Order Updated with unloading");
		rsorder.setData(order);

		return new ResponseEntity<ResponseStructure<Orders>>(rsorder, HttpStatus.OK);
	}

	// FETCH THE ORDER DATA
	public ResponseEntity<ResponseStructure<Orders>> findOrder(int orderid) {
		Orders order = orderRepository.findById(orderid)
				.orElseThrow(() -> new OrderWithIdNotFoundException("Order with id " + orderid + " not found"));

		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("Order with id " + orderid + " data fetched");
		rsorder.setData(order);

		return new ResponseEntity<ResponseStructure<Orders>>(rsorder, HttpStatus.OK);
	}

	// CANCELLING THE ORDER BY ORDER ID
	public ResponseEntity<ResponseStructure<Orders>> removeOrder(int orderid) {
		Orders order = orderRepository.findById(orderid)
				.orElseThrow(() -> new OrderWithIdNotFoundException("Order with id " + orderid + " not found"));

		if (!order.getStatus().equals("Placed")) {
			rsorder.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
			rsorder.setMessage("Order with id " + orderid + " cannot be cancelled");
			rsorder.setData(order);

			return new ResponseEntity<ResponseStructure<Orders>>(rsorder, HttpStatus.NOT_ACCEPTABLE);
		} else {
			order.setCarrier(null);
			order.setCargo(null);
			order.setLoading(null);
			order.setUnloading(null);
			order.setStatus("Cancelled");
		}
		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("Order with id " + orderid + " cancelled");
		rsorder.setData(order);

		return new ResponseEntity<ResponseStructure<Orders>>(rsorder, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Orders>>> findAllOrders() {
		List<Orders> allOrders = orderRepository.findAll();
		ResponseStructure<List<Orders>> rsorder = new ResponseStructure<List<Orders>>();
		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("All orders retrieved");
		rsorder.setData(allOrders);
		return new ResponseEntity<ResponseStructure<List<Orders>>>(rsorder, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Orders>>> cancelledOrders() {
		List<Orders> allorderslist = orderRepository.findAll();
		List<Orders> allcancelledorderslist = new ArrayList<Orders>();

		for (Orders o : allorderslist) {
			if (o.getStatus().equals("Cancelled")) {
				allcancelledorderslist.add(o);
			}
		}
		ResponseStructure<List<Orders>> rsorder = new ResponseStructure<List<Orders>>();
		rsorder.setStatuscode(HttpStatus.OK.value());
		rsorder.setMessage("Cancelled orders list");
		rsorder.setData(allcancelledorderslist);

		return new ResponseEntity<ResponseStructure<List<Orders>>>(rsorder, HttpStatus.OK);
	}

}
