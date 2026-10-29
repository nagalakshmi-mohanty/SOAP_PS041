package com.gridpulse.alertservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gridpulse.alertservice.entity.Alert;
import com.gridpulse.alertservice.repository.AlertRepository;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertRepository alertRepository;

    public AlertController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @GetMapping("/{id}")
    public Alert getAlertById(@PathVariable Long id) {
        return alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
    }

    @PostMapping
    public Alert createAlert(@RequestBody Alert alert) {
        return alertRepository.save(alert);
    }

    @PutMapping("/{id}")
    public Alert updateAlert(
            @PathVariable Long id,
            @RequestBody Alert alert) {

        Alert existing = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        existing.setMeterNumber(alert.getMeterNumber());
        existing.setUsername(alert.getUsername());
        existing.setMessage(alert.getMessage());

        return alertRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public String deleteAlert(@PathVariable Long id) {

        if (!alertRepository.existsById(id)) {
            throw new RuntimeException("Alert not found");
        }

        alertRepository.deleteById(id);

        return "Alert deleted successfully";
    }
}