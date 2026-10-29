package com.gridpulse.usageservice.repository;

import com.gridpulse.usageservice.entity.Usage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRepository extends JpaRepository<Usage, Long> {

}