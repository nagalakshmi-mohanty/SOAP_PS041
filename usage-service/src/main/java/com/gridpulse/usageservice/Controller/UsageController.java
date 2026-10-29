package com.gridpulse.usageservice.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gridpulse.usageservice.entity.Usage;
import com.gridpulse.usageservice.repository.UsageRepository;

@RestController
@RequestMapping("/usage")
public class UsageController {

    private final UsageRepository usageRepository;

    public UsageController(UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    // GET all usage records
    @GetMapping
    public List<Usage> getAllUsage() {
        return usageRepository.findAll();
    }

    // GET usage by ID
    @GetMapping("/{id}")
    public Usage getUsageById(@PathVariable Long id) {
        return usageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usage record not found"));
    }

    // POST create usage
    @PostMapping
    public Usage createUsage(@RequestBody Usage usage) {
        return usageRepository.save(usage);
    }

    // PUT update usage
    @PutMapping("/{id}")
    public Usage updateUsage(
            @PathVariable Long id,
            @RequestBody Usage usage) {

        Usage existing = usageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usage record not found"));

        existing.setMeterNumber(usage.getMeterNumber());
        existing.setUsername(usage.getUsername());
        existing.setUnitsConsumed(usage.getUnitsConsumed());
        existing.setDate(usage.getDate());

        return usageRepository.save(existing);
    }

    // DELETE usage
    @DeleteMapping("/{id}")
    public String deleteUsage(@PathVariable Long id) {

        if (!usageRepository.existsById(id)) {
            throw new RuntimeException("Usage record not found");
        }

        usageRepository.deleteById(id);

        return "Usage record deleted successfully";
    }
}