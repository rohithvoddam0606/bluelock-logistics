package com.alpha.bluelock_logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.bluelock_logistics.entity.Carrier;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Integer>{

}
