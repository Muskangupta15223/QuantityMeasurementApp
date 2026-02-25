package quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.quantitymeasurement.Length;
import com.quantitymeasurement.Length.LengthUnit;
import com.quantitymeasurement.QuantityMeasurementApp;

public class QuantityMeasurementAppTest {
	@Test
	void testEquality_FeetToFeet_SameValue() {
		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(1.0, LengthUnit.Feet);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_InchToInch_SameValue() {
		Length l1 = new Length(1.0, LengthUnit.Inches);
		Length l2 = new Length(1.0, LengthUnit.Inches);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_InchToFeet_EquivalentValue() {
		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(12.0, LengthUnit.Inches);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_FeetToFeet_DifferentValue() {
		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(12.0, LengthUnit.Feet);
		assertFalse(l1.equals(l2));
	}

	@Test
	void testEquality_InchToInch_DifferentValue() {
		Length l1 = new Length(1.0, LengthUnit.Inches);
		Length l2 = new Length(11.0, LengthUnit.Inches);
		assertFalse(l1.equals(l2));
	}

	@Test
	void testEquality_InvalidUnit() {
		assertThrows(NullPointerException.class, () -> {
			new Length(1.0, null);
			});
	}
		@Test
		public void testEquality_NullUnit() {
			assertThrows(NullPointerException.class, () -> new Length(1.0, null));
		}

	@Test
	void testEquality_SameReference() {
		Length l1 = new Length(1.0, Length.LengthUnit.Feet);
		assertTrue(l1.equals(l1));
		Length length = new Length(2.0, Length.LengthUnit.Yards);
		assertTrue(length.equals(length));
	}

	@Test
	void testEquality_NullComparison() {
		Length l1 = new Length(12.0, LengthUnit.Feet);
		assertFalse(l1.equals(null));
		
		Length length = new Length(2.0, Length.LengthUnit.Yards);
		assertFalse(length.equals(null));
	}



	@Test
	public void testEquality_YardToYard_SameValue() {
		assertTrue(new Length(1.0, Length.LengthUnit.Yards).equals(new Length(1.0, Length.LengthUnit.Yards)));
	}

	@Test
	public void testEquality_YardToYard_DifferentValue() {
		assertFalse(new Length(1.0, Length.LengthUnit.Yards).equals(new Length(2.0, Length.LengthUnit.Yards)));
	}

	@Test
	public void testEquality_YardToFeet_EquivalentValue() {
		assertTrue(new Length(1.0, Length.LengthUnit.Yards).equals(new Length(3.0, Length.LengthUnit.Feet)));
	}

	@Test
	public void testEquality_FeetToYard_EquivalentValue() {
		assertTrue(new Length(3.0, Length.LengthUnit.Feet).equals(new Length(1.0, Length.LengthUnit.Yards)));
	}

	@Test
	public void testEquality_YardToInches_EquivalentValue() {
		assertTrue(new Length(1.0, Length.LengthUnit.Yards).equals(new Length(36.0, Length.LengthUnit.Inches)));
	}

	@Test
	public void testEquality_InchesToYard_EquivalentValue() {
		assertTrue(new Length(36.0, Length.LengthUnit.Inches).equals(new Length(1.0, Length.LengthUnit.Yards)));
	}

	@Test
	public void testEquality_CentimetersToCentimeters_SameValue() {
		assertTrue(
				new Length(2.0, Length.LengthUnit.Centimeters).equals(new Length(2.0, Length.LengthUnit.Centimeters)));
	}

	@Test
	public void testEquality_CentimetersToCentimeters_DifferentValue() {
		assertFalse(
				new Length(2.0, Length.LengthUnit.Centimeters).equals(new Length(3.0, Length.LengthUnit.Centimeters)));
	}

	@Test
	public void testEquality_CentimetersToInches_EquivalentValue() {
		assertTrue(new Length(1.0, Length.LengthUnit.Centimeters).equals(new Length(0.393701, Length.LengthUnit.Inches)));
	}

	@Test
	public void testEquality_InchesToCentimeters_EquivalentValue() {
		assertTrue(new Length(0.393701, Length.LengthUnit.Inches).equals(new Length(1.0, Length.LengthUnit.Centimeters)));
	}

	@Test
	public void testEquality_YardToFeet_NonEquivalentValue() {
		assertFalse(new Length(1.0, Length.LengthUnit.Yards).equals(new Length(2.0, Length.LengthUnit.Feet)));
	}

	@Test
	public void testEquality_CentimetersToFeet_NonEquivalentValue() {
		assertFalse(new Length(1.0, Length.LengthUnit.Centimeters).equals(new Length(1.0, Length.LengthUnit.Feet)));
	}

	@Test
	public void testEquality_MultiUnit_TransitiveProperty() {
		Length yard = new Length(1.0, Length.LengthUnit.Yards);
		Length feet = new Length(3.0, Length.LengthUnit.Feet);
		Length inches = new Length(36.0, Length.LengthUnit.Inches);

		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yard.equals(inches));
	}

	@Test
	public void testEquality_AllUnits_ComplexScenario() {
		Length yards = new Length(2.0, Length.LengthUnit.Yards);
		Length feet = new Length(6.0, Length.LengthUnit.Feet);
		Length inches = new Length(72.0, Length.LengthUnit.Inches);

		assertTrue(yards.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yards.equals(inches));
	}

	@Test
	public void testEquality_DifferentClass() {
		Length length = new Length(2.0, Length.LengthUnit.Yards);
		assertFalse(length.equals("2.0 YARDS"));
	}
	@Test
	public void testEquality_NonNumericInput_NaN() {
		assertThrows(IllegalArgumentException.class, () -> new Length(Double.NaN, Length.LengthUnit.Feet));
	}

	@Test
	public void testEquality_NonNumericInput_Infinite() {
		assertThrows(IllegalArgumentException.class,
				() -> new Length(Double.POSITIVE_INFINITY, Length.LengthUnit.Centimeters));
	}

	@Test
	public void testEquality_DemonstrateLengthComparisonMethod() {
		assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(1.0, Length.LengthUnit.Yards, 36.0,
				Length.LengthUnit.Inches));
	}
}
