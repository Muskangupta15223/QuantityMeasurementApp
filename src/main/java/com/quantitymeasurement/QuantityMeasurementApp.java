package com.quantitymeasurement;

public class QuantityMeasurementApp {

	public static void demonstrateFeetEquality() {

		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);

		System.out.println(" Check Feet Equal: " + f1.equals(f2));
	}
	
	public static void demonstrateInchesEquality() {
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(2.0);
		System.out.println("Check Inches Equal : " + i1.equals(i2));
	}
	public static void main(String[] args) {
		
		demonstrateFeetEquality();
		demonstrateInchesEquality();
	}
}
