package com.quantitymeasurement;

public class Inches {
	
	private final double inch;

	public Inches(double inch) {
		this.inch = inch;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Inches other = (Inches)obj;
		return Double.compare(this.inch,other.inch)==0;
	}
}
