package com.quantitymeasurement;

import java.util.Objects;

public class Length {

	private final double value;
	private final LengthUnit unit;
	
	public enum LengthUnit{
		Feet(12.0),
		Inches(1.0),
		Yards(36.0),
		Centimeters(0.393701);
		
		private final double conversionFactor;
		LengthUnit(double conversionFactor){
			this.conversionFactor = conversionFactor;
		}
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	public Length(double value, LengthUnit unit) {
		 if (unit == null) {
		        throw new NullPointerException("Unit cannot be null");
		    }
		 if(Double.isNaN(value) || Double.isInfinite(value)) {
			 throw new IllegalArgumentException("Invalid numeric value");
		 }
		this.value = value;
		this.unit = unit;
	}
	public double convertToBaseUnit() {
		double valueInInches = value * unit.getConversionFactor();
		return Math.round(valueInInches * 100.0) / 100.0;
	}
	public Length convertTo(LengthUnit targetUnit) {
		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit must not be null");
		}

		double valueInInches = this.convertToBaseUnit();
		double convertedValue = valueInInches / targetUnit.getConversionFactor();
		double roundedValue = Math.round(convertedValue * 100.0) / 100.0;

		return new Length(roundedValue, targetUnit);
	}

	public static double convert(double value, LengthUnit source, LengthUnit target) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be a finite number");
		}

		if (source == null || target == null) {
			throw new IllegalArgumentException("Source and target units must not be null");
		}

		double valueInInches = value * source.getConversionFactor();
		double result = valueInInches / target.getConversionFactor();

		return result;
	}

	private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
	    return lengthInInches / targetUnit.getConversionFactor();
	} 
	
	public Length add(Length thatLength) {
		if (thatLength == null) {
			throw new IllegalArgumentException("Length to add must not be null");
		}
		double thisInches = this.convertToBaseUnit();
		double thatInches = thatLength.convertToBaseUnit();
		double sumInches = thisInches + thatInches;
		double resultValue = convertFromBaseToTargetUnit(sumInches, this.unit);
		return new Length(resultValue, this.unit);
	}
	
	public Length add(Length other, LengthUnit targetUnit) {
		if (other == null) {
			throw new IllegalArgumentException("length cannot be null");
		}

		if (targetUnit == null) {
			throw new IllegalArgumentException("target unit not be null");
		}

		if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
			throw new IllegalArgumentException("finite value only");
		}
		double thisInches = this.convertToBaseUnit();
		double otherInches = other.convertToBaseUnit();
		double sumInches = thisInches + otherInches;
		double resultValue = sumInches / targetUnit.getConversionFactor();
		return new Length(resultValue, targetUnit);
	}

	public boolean compare(Length thatLength) {
		 if (thatLength == null) {
	            return false;
	        }
	        return this.equals(thatLength);
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
	@Override
	public String toString() {
		return String.format("%.2f %s", value, unit);
	}
}
