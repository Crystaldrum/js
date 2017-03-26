package com.smee.e.vo;

public class DoroCD {
	private String doroCD;

	
	public DoroCD(){}
	
	public DoroCD(String doroCD) {
		this.doroCD = doroCD;
	}
	public String getDoroCD() {
		return doroCD;
	}
	public void setDoroCD(String doroCD) {
		this.doroCD = doroCD;
	}
	@Override
	public String toString() {
		return "DoroCD [doroCD=" + doroCD + "]";
	}
}
