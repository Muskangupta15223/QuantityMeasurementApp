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
	
	///
	@Test
	public void testConversion_FeetToInches() {
		double result = Length.convert(1.0, Length.LengthUnit.Feet, Length.LengthUnit.Inches);
		assertEquals(12.0, result);
	}

	@Test
	public void testConversion_InchesToFeet() {
		double result = Length.convert(24.0, Length.LengthUnit.Inches, Length.LengthUnit.Feet);
		assertEquals(2.0, result);
	}

	@Test
	public void testConversion_YardsToInches() {
		double result = Length.convert(1.0, Length.LengthUnit.Yards, Length.LengthUnit.Inches);
		assertEquals(36.0, result);
	}

	@Test
	public void testConversion_CentimetersToInches() {
		double result = Length.convert(2.54, Length.LengthUnit.Centimeters, Length.LengthUnit.Inches);
		assertEquals(1.0, result, 1e-6);
	}

	@Test
	public void testConversion_ZeroValue() {
		double result = Length.convert(0.0, Length.LengthUnit.Feet, Length.LengthUnit.Inches);
		assertEquals(0.0, result);
	}

	@Test
	public void testConversion_NegativeValue() {
		double result = Length.convert(-1.0, Length.LengthUnit.Feet, Length.LengthUnit.Inches);
		assertEquals(-12.0, result);
	}

	@Test
	public void testConversion_SameUnit() {
		double result = Length.convert(5.0, Length.LengthUnit.Feet, Length.LengthUnit.Feet);
		assertEquals(5.0, result);
	}

	@Test
	public void testConversion_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> Length.convert(1.0, null, Length.LengthUnit.Feet));
	}

	@Test
	public void testConversion_NaN() {
		assertThrows(IllegalArgumentException.class,
				() -> Length.convert(Double.NaN, Length.LengthUnit.Feet, Length.LengthUnit.Inches));
	}

	@Test
	public void testConversion_Infinite() {
		assertThrows(IllegalArgumentException.class,
				() -> Length.convert(Double.POSITIVE_INFINITY, Length.LengthUnit.Feet, Length.LengthUnit.Inches));
	}

	@Test
	public void testConversion_InchesToYards() {
		double result = Length.convert(72.0, Length.LengthUnit.Inches, Length.LengthUnit.Yards);
		assertEquals(2.0, result, 1e-6);
	}

	@Test
	public void testConversion_FeetToYards() {
		double result = Length.convert(6.0, Length.LengthUnit.Feet, Length.LengthUnit.Yards);
		assertEquals(2.0, result, 1e-6);
	}

	@Test
	public void testConversion_YardsToFeet() {
		double result = Length.convert(2.0, Length.LengthUnit.Yards, Length.LengthUnit.Feet);
		assertEquals(6.0, result, 1e-6);
	}

	@Test
	public void testConversion_RoundTrip_PreservesValue() {
		double original = 5.0;
		double toInches = Length.convert(original, Length.LengthUnit.Feet, Length.LengthUnit.Inches);
		double backToFeet = Length.convert(toInches, Length.LengthUnit.Inches, Length.LengthUnit.Feet);
		assertEquals(original, backToFeet, 1e-6);
	}

	@Test
	public void testConversion_PrecisionTolerance() {
		double result = Length.convert(100.0, Length.LengthUnit.Centimeters, Length.LengthUnit.Feet);
		assertEquals(3.28, result, 0.01);
	}

	@Test
	public void testConvertTo_FeetToInches() {
		Length feet = new Length(1.0, Length.LengthUnit.Feet);
		Length inches = feet.convertTo(Length.LengthUnit.Inches);
		assertEquals(12.0, inches.toString().contains("12.00") ? 12.0 : 0.0, 0.01);
	}

	@Test
	public void testConvertTo_YardsToInches() {
		Length yards = new Length(2.0, Length.LengthUnit.Yards);
		Length inches = yards.convertTo(Length.LengthUnit.Inches);
		assertTrue(inches.toString().contains("72.00"));
	}

	@Test
	public void testConvertTo_NullUnit() {
		Length length = new Length(1.0, Length.LengthUnit.Feet);
		assertThrows(IllegalArgumentException.class, () -> length.convertTo(null));
	}

	@Test
	public void testConvertTo_Immutability() {
		Length original = new Length(5.0, Length.LengthUnit.Feet);
		Length converted = original.convertTo(Length.LengthUnit.Inches);
		assertNotSame(original, converted);
		assertTrue(original.equals(new Length(5.0, Length.LengthUnit.Feet)));
	}

	@Test
	public void testToString_Format() {
		Length length = new Length(12.5, Length.LengthUnit.Inches);
		String result = length.toString();
		assertTrue(result.contains("12.50") && result.contains("Inches"));
	}

	@Test
	public void testDemonstrateLengthConversion_StaticMethod() {
		double result = QuantityMeasurementApp.demonstrateLengthConversion(3.0, Length.LengthUnit.Feet,Length.LengthUnit.Inches);
		assertEquals(36.0, result, 1e-6);
	}

	@Test
	public void testDemonstrateLengthConversion_InstanceMethodOverload() {
		Length lengthInYards = new Length(2.0, Length.LengthUnit.Yards);
		Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(lengthInYards,
				Length.LengthUnit.Inches);
		assertTrue(lengthInInches.toString().contains("72.00"));
	}

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length l1 = new Length(1.0, LengthUnit.Feet);
        Length l2 = new Length(2.0, LengthUnit.Feet);

        Length result = l1.add(l2);
        assertEquals(new Length(3.0, LengthUnit.Feet), result);
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length l1 = new Length(6.0, LengthUnit.Inches);
        Length l2 = new Length(6.0, LengthUnit.Inches);

        Length result = l1.add(l2);
        assertEquals(new Length(12.0, LengthUnit.Inches), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length l1 = new Length(1.0, LengthUnit.Feet);
        Length l2 = new Length(12.0, LengthUnit.Inches);

        Length result = l1.add(l2);
        assertEquals(new Length(2.0, LengthUnit.Feet), result);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length l1 = new Length(12.0, LengthUnit.Inches);
        Length l2 = new Length(1.0, LengthUnit.Feet);

        Length result = l1.add(l2);
        assertEquals(new Length(24.0, LengthUnit.Inches), result);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length l1 = new Length(1.0, LengthUnit.Yards);
        Length l2 = new Length(3.0, LengthUnit.Feet);

        Length result = l1.add(l2);
        assertEquals(new Length(2.0, LengthUnit.Yards), result);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length l1 = new Length(2.54, LengthUnit.Centimeters);
        Length l2 = new Length(1.0, LengthUnit.Inches);

        Length result = l1.add(l2);
        assertTrue(result.equals(new Length(5.08, LengthUnit.Centimeters)));
    }

    @Test
    public void testAddition_Commutativity() {
        Length l1 = new Length(1.0, LengthUnit.Feet);
        Length l2 = new Length(12.0, LengthUnit.Inches);

        assertTrue(l1.add(l2).equals(l2.add(l1)));
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, LengthUnit.Feet);
        Length l2 = new Length(0.0, LengthUnit.Inches);

        Length result = l1.add(l2);
        assertEquals(new Length(5.0, LengthUnit.Feet), result);
    }

    @Test
    public void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, LengthUnit.Feet);
        Length l2 = new Length(-2.0, LengthUnit.Feet);

        Length result = l1.add(l2);
        assertEquals(new Length(3.0, LengthUnit.Feet), result);
    }

    @Test
    public void testAddition_NullSecondOperand() {
        Length l1 = new Length(1.0, LengthUnit.Feet);
        assertThrows(IllegalArgumentException.class, () -> l1.add(null));
    }

    @Test
    public void testAddition_LargeValues() {
        Length l1 = new Length(1e6, LengthUnit.Feet);
        Length l2 = new Length(1e6, LengthUnit.Feet);

        Length result = l1.add(l2);
        assertEquals(new Length(2e6, LengthUnit.Feet), result);
    }

    @Test
    public void testAddition_SmallValues() {
        Length l1 = new Length(0.01, LengthUnit.Feet);
        Length l2 = new Length(0.02, LengthUnit.Feet);

        Length result = l1.add(l2);
        assertEquals(new Length(0.03, LengthUnit.Feet), result);
    }
}

