package com.alpha.bluelock_logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.bluelock_logistics.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{

}
