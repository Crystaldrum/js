package com.smee.e;

public class aptName {
	private String aptName;

	
	public aptName() {
		super();
	}

	public aptName(String aptName) {
		super();
		this.aptName = aptName;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	@Override
	public String toString() {
		return "aptName [aptName=" + aptName + "]";
	}
	
	
}
