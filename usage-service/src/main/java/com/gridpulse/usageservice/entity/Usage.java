package com.gridpulse.usageservice.entity;
	import jakarta.persistence.*;

	@Entity
	@Table(name = "usage_records")
	public class Usage {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String meterNumber;

	    private String username;

	    private Double unitsConsumed;

	    private String date;

	    public Usage() {
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getMeterNumber() {
	        return meterNumber;
	    }

	    public void setMeterNumber(String meterNumber) {
	        this.meterNumber = meterNumber;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public Double getUnitsConsumed() {
	        return unitsConsumed;
	    }

	    public void setUnitsConsumed(Double unitsConsumed) {
	        this.unitsConsumed = unitsConsumed;
	    }

	    public String getDate() {
	        return date;
	    }

	    public void setDate(String date) {
	        this.date = date;
	    }
	}
