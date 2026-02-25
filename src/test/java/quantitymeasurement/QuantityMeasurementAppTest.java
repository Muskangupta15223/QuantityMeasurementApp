package quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.quantitymeasurement.Length;
import com.quantitymeasurement.Length.LengthUnit;

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
	void testEquality_NullUnit() {
		Length l1 = new Length(1.0, Length.LengthUnit.Feet);
		assertFalse(l1.equals(null));
	}

	@Test
	void testEquality_SameReference() {
		Length l1 = new Length(1.0, Length.LengthUnit.Feet);
		assertTrue(l1.equals(l1));
	}

	@Test
	void testEquality_NullComparison() {
		Length l1 = new Length(12.0, LengthUnit.Feet);
		assertFalse(l1.equals(null));
	}
	
}
