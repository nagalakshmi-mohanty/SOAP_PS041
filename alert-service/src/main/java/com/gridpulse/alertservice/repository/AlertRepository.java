package com.gridpulse.alertservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gridpulse.alertservice.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long> {

}