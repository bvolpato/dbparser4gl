package org.brunocunha.dbparser.vo;

public class Sequence {
	private String name;
	private int initial;
	private int increment;
	private boolean cycleOnLimit;
	private long minVal;
	private long maxVal;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInitial() {
		return initial;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public boolean isCycleOnLimit() {
		return cycleOnLimit;
	}

	public void setCycleOnLimit(boolean cycleOnLimit) {
		this.cycleOnLimit = cycleOnLimit;
	}

	public long getMinVal() {
		return minVal;
	}

	public void setMinVal(long minVal) {
		this.minVal = minVal;
	}

	public long getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(long maxVal) {
		this.maxVal = maxVal;
	}

}
