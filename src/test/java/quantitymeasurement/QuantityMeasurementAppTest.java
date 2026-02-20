package quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.quantitymeasurement.Feet;
import com.quantitymeasurement.Inches;
import com.quantitymeasurement.Length;
import com.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementAppTest {

	@Test
	void testFeetEquality_SameValue() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);
		assertTrue(f1.equals(f2));
	}

	@Test
	void testFeetEquality_DifferentValue() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(2.0);
		assertFalse(f1.equals(f2));
	}

	@Test
	void testFeetEquality_NullComparison() {
		Feet f1 = new Feet(1.0);
		assertFalse(f1.equals(null));
	}

	@Test
	void testFeetEquality_DifferentClass() {
		Feet f1 = new Feet(1.0);
		String str = "1.0";
		assertFalse(f1.equals(str));
	}

	@Test
	void testFeetEquality_SameReference() {
		Feet f1 = new Feet(1.0);
		assertTrue(f1.equals(f1));
	}

	@Test
	void testInchesEquality_SameValue() {
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(1.0);
		assertTrue(i1.equals(i2));
	}

	@Test
	void testInchesEquality_DifferentValue() {
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(2.0);
		assertFalse(i1.equals(i2));
	}

	@Test
	void testInchesEquality_NullComparison() {
		Inches i1 = new Inches(1.0);
		assertFalse(i1.equals(null));
	}

	@Test
	void testInchesEquality_DifferentClass() {
		Inches f1 = new Inches(1.0);
		String str = "1.0";
		assertFalse(f1.equals(str));
	}

	@Test
	void testInchesEquality_SameReference() {
		Inches i1 = new Inches(1.0);
		assertTrue(i1.equals(i1));
	}

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
