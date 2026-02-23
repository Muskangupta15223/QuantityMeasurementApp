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
		Length l1 = new Length(1,  LengthUnit.Feet);
		Length l2 = new Length(12, LengthUnit.Inches);

		System.out.println("Using equals: " + demonstrateLengthEquality(l1, l2));
		System.out.println("Using compare: " + l1.compare(l2));
	}

	public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2,
			LengthUnit unit2) {
		Length l1 = new Length(value1, unit1);
		Length l2 = new Length(value2, unit2);
		boolean result = l1.equals(l2);
		System.out.println("length are equal :" + result);
		return result;
	}

	public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
		double result = Length.convert(value, from, to);
		System.out.println(value + " " + from + " = " + result + " " + to);
		return result;
	}

	public static Length demonstrateLengthConversion(Length length,LengthUnit toUnit) {
		Length result = length.convertTo(toUnit);
		System.out.println(length + " = " + result);
		return result;
	}

	public static Length demonstrateLengthAddition(Length length1, Length length2) {
		Length result = length1.add(length2);
		System.out.println("Addition result: " + result);
		return result;
	}
	
	public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit) {
		Length result = l1.add(l2, targetUnit);
		System.out.println("Addition : " + result);
		return result;
	}
	
	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();

		demonstrateLengthComparison(1.0, LengthUnit.Feet, 12.0, LengthUnit.Inches);
		demonstrateLengthComparison(1.0, LengthUnit.Yards, 3.0, LengthUnit.Feet);
		demonstrateLengthComparison(1.0, LengthUnit.Yards, 36.0,LengthUnit.Inches);
		demonstrateLengthComparison(1.0, LengthUnit.Centimeters, 0.393701,LengthUnit.Inches);
		demonstrateLengthComparison(2.0, LengthUnit.Yards, 6.0, LengthUnit.Feet);
		demonstrateLengthConversion(1.0, LengthUnit.Feet, LengthUnit.Inches);
		demonstrateLengthConversion(3.0, LengthUnit.Yards,LengthUnit.Feet);
		demonstrateLengthConversion(2.54,LengthUnit.Centimeters,LengthUnit.Inches);
		
		Length lengthInYards = new Length(2.0,LengthUnit.Yards);
		demonstrateLengthConversion(lengthInYards, LengthUnit.Inches);

		Length lengthInFeet = new Length(3.0, LengthUnit.Feet);
		demonstrateLengthConversion(lengthInFeet,LengthUnit.Yards);
		
		System.out.println("\n=== UC6: Addition Operations ===");
		demonstrateLengthAddition(new Length(1.0,  LengthUnit.Feet), new Length(12.0, LengthUnit.Inches));
		demonstrateLengthAddition(new Length(1.0,  LengthUnit.Yards), new Length(3.0, LengthUnit.Feet));
		demonstrateLengthAddition(new Length(2.54, LengthUnit.Centimeters),new Length(1.0, LengthUnit.Inches));
		
		demonstrateLengthAddition(new Length(1.0, LengthUnit.Feet), new Length(12.0, LengthUnit.Inches), LengthUnit.Yards);
	}
}
