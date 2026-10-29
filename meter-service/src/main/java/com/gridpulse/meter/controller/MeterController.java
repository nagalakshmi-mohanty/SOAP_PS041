package com.gridpulse.meter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gridpulse.meter.entity.Meter;
import com.gridpulse.meter.repository.MeterRepository;

@RestController
@RequestMapping("/meters")
public class MeterController {

    private final MeterRepository meterRepository;

    public MeterController(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    @GetMapping
    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Meter getMeterById(@PathVariable Long id) {
        return meterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meter not found"));
    }

    @PostMapping
    public Meter createMeter(@RequestBody Meter meter) {
        return meterRepository.save(meter);
    }

    @PutMapping("/{id}")
    public Meter updateMeter(@PathVariable Long id,
                             @RequestBody Meter meter) {

        Meter existingMeter = meterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meter not found"));

        existingMeter.setMeterNumber(meter.getMeterNumber());
        existingMeter.setUsername(meter.getUsername());
        existingMeter.setReading(meter.getReading());
        existingMeter.setStatus(meter.getStatus());

        return meterRepository.save(existingMeter);
    }

    @DeleteMapping("/{id}")
    public String deleteMeter(@PathVariable Long id) {

        meterRepository.deleteById(id);

        return "Meter deleted successfully";
    }
}