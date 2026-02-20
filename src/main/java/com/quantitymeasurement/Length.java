package com.quantitymeasurement;

import java.util.Objects;

public class Length {

	private double value;
	private LengthUnit unit;
	
	public enum LengthUnit{
		Feet(12.0),
		Inches(1.0);
		
		private final double conversionFactor;
		LengthUnit(double conversionFactor){
			this.conversionFactor = conversionFactor;
		}
		public double toBaseUnit(double value) {
			return conversionFactor * value;
		}
	}
	
	public Length(double value, LengthUnit unit) {
		 if (unit == null) {
		        throw new NullPointerException("Unit cannot be null");
		    }
		this.value = value;
		this.unit = unit;
	}
	public double convertToBaseUnit() {
		return unit.toBaseUnit(value);
	}
	public boolean compare(Length thatLength) {
		 if (thatLength == null) {
	            return false;
	        }
	        return Double.compare(this.convertToBaseUnit(),
	                              thatLength.convertToBaseUnit()) == 0;
	    }
	
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
		Length other = (Length)obj;
		return Double.compare(this.convertToBaseUnit(), other.convertToBaseUnit())==0;
	}
	@Override
	public int hashCode() {
		return Objects.hash(convertToBaseUnit());
	}
}
