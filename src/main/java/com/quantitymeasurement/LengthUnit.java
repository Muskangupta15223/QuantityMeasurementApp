package com.quantitymeasurement;

public enum LengthUnit {

    Feet(12.0),
    Inches(1.0),
    Yards(36.0),
    Centimeters(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;   
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor; 
    }
}