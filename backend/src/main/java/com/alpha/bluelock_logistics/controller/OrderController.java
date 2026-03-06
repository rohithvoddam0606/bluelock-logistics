package com.alpha.bluelock_logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.bluelock_logistics.dto.OrderDto;
import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.Orders;
import com.alpha.bluelock_logistics.service.OrderService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders() {
		return orderService.findAllOrders();
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderById(@PathVariable int id) {
		return orderService.findOrder(id);
	}

	@PostMapping("/orders")
	public ResponseEntity<ResponseStructure<Orders>> addOrder(@RequestBody OrderDto orderDto) {
		return orderService.saveOrder(orderDto);
	}

	@PostMapping("/addorder")
	public ResponseEntity<ResponseStructure<Orders>> addOrderOld(@RequestBody OrderDto orderDto) {
		return orderService.saveOrder(orderDto);
	}

	@PutMapping("/updateorder/{orderid}/assigncarrier/{truckid}")
	public ResponseEntity<ResponseStructure<Orders>> updateOrder(@PathVariable int orderid, @PathVariable int truckid) {
		return orderService.modifyOrder(orderid, truckid);
	}

	@PutMapping("/updateorder/{orderid}/updateloading")
	public ResponseEntity<ResponseStructure<Orders>> updateLoading(@PathVariable int orderid) {
		return orderService.modifyLoading(orderid);
	}

	@PutMapping("/updateorder/{orderid}/updateunloading")
	public ResponseEntity<ResponseStructure<Orders>> updateUnloading(@PathVariable int orderid) {
		return orderService.modifyUnloading(orderid);
	}

	@GetMapping("/fetchorder/{orderid}")
	public ResponseEntity<ResponseStructure<Orders>> fetchOrder(@PathVariable int orderid) {
		return orderService.findOrder(orderid);
	}

	@GetMapping("/getallcancelledorders")
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllCancelledOrders() {
		return orderService.cancelledOrders();
	}
}
