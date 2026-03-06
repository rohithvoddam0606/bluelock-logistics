package com.alpha.bluelock_logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.bluelock_logistics.entity.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer>{

}
