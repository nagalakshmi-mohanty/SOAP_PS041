package com.gridpulse.meter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gridpulse.meter.entity.Meter;

public interface MeterRepository extends JpaRepository<Meter, Long> {

}