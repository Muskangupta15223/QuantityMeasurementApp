package com.quantitymeasurement;

public class QuantityMeasurementApp {


	public static void demonstrateFeetEquality() {
		Length l1 = new Length(1.0, Length.LengthUnit.Feet);
		Length l2 = new Length(1.0, Length.LengthUnit.Feet);

		System.out.println("feet equal : " + l1.equals(l2));
	}

	public static void demonstrateInchesEquality() {
		Length l1 = new Length(1.0, Length.LengthUnit.Inches);
		Length l2 = new Length(1.0, Length.LengthUnit.Inches);

		System.out.println("inches equal : " + l1.equals(l2));
	}

	public static boolean demonstrateLengthEquality(Length l1, Length l2) {
		return l1.equals(l2);
	}

	public static void demonstrateFeetInchesComparison() {
		Length l1 = new Length(1, Length.LengthUnit.Feet);
		Length l2 = new Length(12, Length.LengthUnit.Inches);
		
		System.out.println("Using equals: " + demonstrateLengthEquality(l1, l2));
		System.out.println("Using compare: " + l1.compare(l2));
	}

	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();
	}
}
