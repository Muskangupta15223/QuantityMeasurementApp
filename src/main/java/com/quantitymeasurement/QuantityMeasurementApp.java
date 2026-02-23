package com.quantitymeasurement;

public class QuantityMeasurementApp {

	public static void demonstrateFeetEquality() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);
		System.out.println("Check Feet Equal: " + f1.equals(f2));
	}

	public static void demonstrateInchesEquality() {
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(2.0);
		System.out.println("Check Inches Equal : " + i1.equals(i2));
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

	public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2 , Length.LengthUnit unit2) {
		Length l1 = new Length(value1, unit1);
		Length l2 = new Length(value2,unit2);
		boolean result = l1.equals(l2);
		System.out.println("length are equal :" + result);
		return result;
	}
	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();

		demonstrateLengthComparison(1.0, Length.LengthUnit.Feet, 12.0, Length.LengthUnit.Inches);
		demonstrateLengthComparison(1.0, Length.LengthUnit.Yards, 3.0, Length.LengthUnit.Feet);
		demonstrateLengthComparison(1.0, Length.LengthUnit.Yards, 36.0, Length.LengthUnit.Inches);
		demonstrateLengthComparison(1.0, Length.LengthUnit.Centimeters, 0.393701, Length.LengthUnit.Inches);
		demonstrateLengthComparison(2.0, Length.LengthUnit.Yards, 6.0, Length.LengthUnit.Feet);
	}
}
