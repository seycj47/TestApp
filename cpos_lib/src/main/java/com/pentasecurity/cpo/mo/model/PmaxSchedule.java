package com.pentasecurity.cpo.mo.model;

public class PmaxSchedule {
	private final int start;
	private final int duration;
	private final int  multiplier;
	private final String unit;
	private final int value;

	public PmaxSchedule(int start, int duration, int multiplier, String unit, int value){
		this.start = start;
		this.duration = duration;
		this.multiplier = multiplier;
		this.unit = unit;
		this.value = value;
	}
}
