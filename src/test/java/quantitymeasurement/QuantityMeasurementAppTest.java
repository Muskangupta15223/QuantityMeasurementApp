package quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.quantitymeasurement.Length;
import com.quantitymeasurement.LengthUnit;
import com.quantitymeasurement.QuantityMeasurementApp;
import com.quantitymeasurement.Weight;
import com.quantitymeasurement.WeightUnit;

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
		Length l1 = new Length(1.0, LengthUnit.Feet);
		assertTrue(l1.equals(l1));
		Length length = new Length(2.0, LengthUnit.Yards);
		assertTrue(length.equals(length));
	}

	@Test
	void testEquality_NullComparison() {
		Length l1 = new Length(12.0, LengthUnit.Feet);
		assertFalse(l1.equals(null));
		
		Length length = new Length(2.0,LengthUnit.Yards);
		assertFalse(length.equals(null));
	}



	@Test
	public void testEquality_YardToYard_SameValue() {
		assertTrue(new Length(1.0, LengthUnit.Yards).equals(new Length(1.0, LengthUnit.Yards)));
	}

	@Test
	public void testEquality_YardToYard_DifferentValue() {
		assertFalse(new Length(1.0, LengthUnit.Yards).equals(new Length(2.0, LengthUnit.Yards)));
	}

	@Test
	public void testEquality_YardToFeet_EquivalentValue() {
		assertTrue(new Length(1.0, LengthUnit.Yards).equals(new Length(3.0, LengthUnit.Feet)));
	}

	@Test
	public void testEquality_FeetToYard_EquivalentValue() {
		assertTrue(new Length(3.0, LengthUnit.Feet).equals(new Length(1.0, LengthUnit.Yards)));
	}

	@Test
	public void testEquality_YardToInches_EquivalentValue() {
		assertTrue(new Length(1.0, LengthUnit.Yards).equals(new Length(36.0, LengthUnit.Inches)));
	}

	@Test
	public void testEquality_InchesToYard_EquivalentValue() {
		assertTrue(new Length(36.0, LengthUnit.Inches).equals(new Length(1.0, LengthUnit.Yards)));
	}

	@Test
	public void testEquality_CentimetersToCentimeters_SameValue() {
		assertTrue(
				new Length(2.0, LengthUnit.Centimeters).equals(new Length(2.0, LengthUnit.Centimeters)));
	}

	@Test
	public void testEquality_CentimetersToCentimeters_DifferentValue() {
		assertFalse(
				new Length(2.0, LengthUnit.Centimeters).equals(new Length(3.0, LengthUnit.Centimeters)));
	}

	@Test
	public void testEquality_CentimetersToInches_EquivalentValue() {
		assertTrue(new Length(1.0, LengthUnit.Centimeters).equals(new Length(0.393701, LengthUnit.Inches)));
	}

	@Test
	public void testEquality_InchesToCentimeters_EquivalentValue() {
		assertTrue(new Length(0.393701, LengthUnit.Inches).equals(new Length(1.0, LengthUnit.Centimeters)));
	}

	@Test
	public void testEquality_YardToFeet_NonEquivalentValue() {
		assertFalse(new Length(1.0,LengthUnit.Yards).equals(new Length(2.0, LengthUnit.Feet)));
	}

	@Test
	public void testEquality_CentimetersToFeet_NonEquivalentValue() {
		assertFalse(new Length(1.0, LengthUnit.Centimeters).equals(new Length(1.0, LengthUnit.Feet)));
	}

	@Test
	public void testEquality_MultiUnit_TransitiveProperty() {
		Length yard = new Length(1.0, LengthUnit.Yards);
		Length feet = new Length(3.0, LengthUnit.Feet);
		Length inches = new Length(36.0, LengthUnit.Inches);

		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yard.equals(inches));
	}

	@Test
	public void testEquality_AllUnits_ComplexScenario() {
		Length yards = new Length(2.0, LengthUnit.Yards);
		Length feet = new Length(6.0, LengthUnit.Feet);
		Length inches = new Length(72.0, LengthUnit.Inches);

		assertTrue(yards.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yards.equals(inches));
	}

	@Test
	public void testEquality_DifferentClass() {
		Length length = new Length(2.0, LengthUnit.Yards);
		assertFalse(length.equals("2.0 YARDS"));
	}
	@Test
	public void testEquality_NonNumericInput_NaN() {
		assertThrows(IllegalArgumentException.class, () -> new Length(Double.NaN, LengthUnit.Feet));
	}

	@Test
	public void testEquality_NonNumericInput_Infinite() {
		assertThrows(IllegalArgumentException.class,
				() -> new Length(Double.POSITIVE_INFINITY, LengthUnit.Centimeters));
	}

	@Test
	public void testEquality_DemonstrateLengthComparisonMethod() {
		assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(1.0, LengthUnit.Yards, 36.0,
				LengthUnit.Inches));
	}
	
	///
	@Test
	public void testConversion_FeetToInches() {
		double result = Length.convert(1.0, LengthUnit.Feet, LengthUnit.Inches);
		assertEquals(12.0, result);
	}

	@Test
	public void testConversion_InchesToFeet() {
		double result = Length.convert(24.0, LengthUnit.Inches, LengthUnit.Feet);
		assertEquals(2.0, result);
	}

	@Test
	public void testConversion_YardsToInches() {
		double result = Length.convert(1.0, LengthUnit.Yards, LengthUnit.Inches);
		assertEquals(36.0, result);
	}

	@Test
	public void testConversion_CentimetersToInches() {
		double result = Length.convert(2.54, LengthUnit.Centimeters, LengthUnit.Inches);
		assertEquals(1.0, result, 1e-6);
	}

	@Test
	public void testConversion_ZeroValue() {
		double result = Length.convert(0.0, LengthUnit.Feet, LengthUnit.Inches);
		assertEquals(0.0, result);
	}

	@Test
	public void testConversion_NegativeValue() {
		double result = Length.convert(-1.0, LengthUnit.Feet, LengthUnit.Inches);
		assertEquals(-12.0, result);
	}

	@Test
	public void testConversion_SameUnit() {
		double result = Length.convert(5.0, LengthUnit.Feet,LengthUnit.Feet);
		assertEquals(5.0, result);
	}

	@Test
	public void testConversion_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> Length.convert(1.0, null, LengthUnit.Feet));
	}

	@Test
	public void testConversion_NaN() {
		assertThrows(IllegalArgumentException.class,
				() -> Length.convert(Double.NaN, LengthUnit.Feet, LengthUnit.Inches));
	}

	@Test
	public void testConversion_Infinite() {
		assertThrows(IllegalArgumentException.class,
				() -> Length.convert(Double.POSITIVE_INFINITY, LengthUnit.Feet, LengthUnit.Inches));
	}

	@Test
	public void testConversion_InchesToYards() {
		double result = Length.convert(72.0, LengthUnit.Inches, LengthUnit.Yards);
		assertEquals(2.0, result, 1e-6);
	}

	@Test
	public void testConversion_FeetToYards() {
		double result = Length.convert(6.0, LengthUnit.Feet, LengthUnit.Yards);
		assertEquals(2.0, result, 1e-6);
	}

	@Test
	public void testConversion_YardsToFeet() {
		double result = Length.convert(2.0, LengthUnit.Yards, LengthUnit.Feet);
		assertEquals(6.0, result, 1e-6);
	}

	@Test
	public void testConversion_RoundTrip_PreservesValue() {
		double original = 5.0;
		double toInches = Length.convert(original, LengthUnit.Feet, LengthUnit.Inches);
		double backToFeet = Length.convert(toInches, LengthUnit.Inches, LengthUnit.Feet);
		assertEquals(original, backToFeet, 1e-6);
	}

	@Test
	public void testConversion_PrecisionTolerance() {
		double result = Length.convert(100.0, LengthUnit.Centimeters, LengthUnit.Feet);
		assertEquals(3.28, result, 0.01);
	}

	@Test
	public void testConvertTo_FeetToInches() {
		Length feet = new Length(1.0, LengthUnit.Feet);
		Length inches = feet.convertTo(LengthUnit.Inches);
		assertEquals(12.0, inches.toString().contains("12.00") ? 12.0 : 0.0, 0.01);
	}

	@Test
	public void testConvertTo_YardsToInches() {
		Length yards = new Length(2.0, LengthUnit.Yards);
		Length inches = yards.convertTo(LengthUnit.Inches);
		assertTrue(inches.toString().contains("72.00"));
	}

	@Test
	public void testConvertTo_NullUnit() {
		Length length = new Length(1.0,LengthUnit.Feet);
		assertThrows(IllegalArgumentException.class, () -> length.convertTo(null));
	}

	@Test
	public void testConvertTo_Immutability() {
		Length original = new Length(5.0,LengthUnit.Feet);
		Length converted = original.convertTo(LengthUnit.Inches);
		assertNotSame(original, converted);
		assertTrue(original.equals(new Length(5.0,LengthUnit.Feet)));
	}

	@Test
	public void testToString_Format() {
		Length length = new Length(12.5, LengthUnit.Inches);
		String result = length.toString();
		assertTrue(result.contains("12.50") && result.contains("Inches"));
	}

	@Test
	public void testDemonstrateLengthConversion_StaticMethod() {
		double result = QuantityMeasurementApp.demonstrateLengthConversion(3.0, LengthUnit.Feet,LengthUnit.Inches);
		assertEquals(36.0, result, 1e-6);
	}

	@Test
	public void testDemonstrateLengthConversion_InstanceMethodOverload() {
		Length lengthInYards = new Length(2.0, LengthUnit.Yards);
		Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(lengthInYards,
			LengthUnit.Inches);
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

	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {

		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(12.0, LengthUnit.Inches);

		Length result = l1.add(l2, LengthUnit.Feet);

		assertEquals(new Length(2.0, LengthUnit.Feet), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {

		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(12.0, LengthUnit.Inches);

		Length result = l1.add(l2, LengthUnit.Inches);

		assertEquals(new Length(24.0, LengthUnit.Inches), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {

		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(12.0, LengthUnit.Inches);

		Length result = l1.add(l2, LengthUnit.Yards);

		assertTrue(result.equals(new Length(0.6666666667, LengthUnit.Yards)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_NullTargetUnit() {

		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(12.0, LengthUnit.Inches);

		assertThrows(IllegalArgumentException.class, () -> l1.add(l2, null));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

		Length l1 = new Length(2.0, LengthUnit.Yards);
		Length l2 = new Length(3.0, LengthUnit.Feet);
		Length result = l1.add(l2, LengthUnit.Yards);
		assertEquals(new Length(3.0, LengthUnit.Yards), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

		Length l1 = new Length(2.0, LengthUnit.Yards);
		Length l2 = new Length(3.0, LengthUnit.Feet);

		Length result = l1.add(l2, LengthUnit.Feet);

		assertEquals(new Length(9.0, LengthUnit.Feet), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Commutativity() {

		Length l1 = new Length(1.0, LengthUnit.Feet);
		Length l2 = new Length(12.0, LengthUnit.Inches);

		Length result1 = l1.add(l2, LengthUnit.Yards);
		Length result2 = l2.add(l1, LengthUnit.Yards);

		assertTrue(result1.equals(result2));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_WithZero() {

		Length l1 = new Length(5.0, LengthUnit.Feet);
		Length l2 = new Length(0.0, LengthUnit.Inches);

		Length result = l1.add(l2, LengthUnit.Yards);

		assertTrue(result.equals(new Length(1.6666666667, LengthUnit.Yards)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_NegativeValues() {

		Length l1 = new Length(5.0, LengthUnit.Feet);
		Length l2 = new Length(-2.0, LengthUnit.Feet);

		Length result = l1.add(l2, LengthUnit.Inches);

		assertEquals(new Length(36.0, LengthUnit.Inches), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

		Length l1 = new Length(1000.0, LengthUnit.Feet);
		Length l2 = new Length(500.0, LengthUnit.Feet);

		Length result = l1.add(l2, LengthUnit.Inches);

		assertEquals(new Length(18000.0, LengthUnit.Inches), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

		Length l1 = new Length(12.0, LengthUnit.Inches);
		Length l2 = new Length(12.0, LengthUnit.Inches);

		Length result = l1.add(l2, LengthUnit.Yards);

		assertTrue(result.equals(new Length(0.6666666667, LengthUnit.Yards)));
	}
	@Test
	public void testEquality_KilogramToKilogram_SameValue() {
		assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_KilogramToKilogram_DifferentValue() {
		assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(2.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_GramToGram_SameValue() {
		assertTrue(new Weight(100.0, WeightUnit.GRAM).equals(new Weight(100.0, WeightUnit.GRAM)));
	}

	@Test
	public void testEquality_PoundToPound_SameValue() {
		assertTrue(new Weight(2.0, WeightUnit.POUND).equals(new Weight(2.0, WeightUnit.POUND)));
	}

	@Test
	public void testEquality_KilogramToGram_EquivalentValue() {
		assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testEquality_GramToKilogram_EquivalentValue() {
		assertTrue(new Weight(1000.0, WeightUnit.GRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_KilogramToPound_EquivalentValue() {
		assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(2.20462, WeightUnit.POUND)));
	}

	@Test
	public void testEquality_GramToPound_EquivalentValue() {
		assertTrue(new Weight(453.592, WeightUnit.GRAM).equals(new Weight(1.0, WeightUnit.POUND)));
	}

	@Test
	public void testEquality_WeightVsLength_Incompatible() {
		Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
		Length length = new Length(1.0, LengthUnit.Feet);
		assertFalse(weight.equals(length));
	}

	@Test
	public void testEquality_Weight_NullComparison() {
		Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
		assertFalse(weight.equals(null));
	}

	@Test
	public void testEquality_Weight_SameReference() {
		Weight weight = new Weight(2.0, WeightUnit.KILOGRAM);
		assertTrue(weight.equals(weight));
	}

	@Test
	public void testEquality_Weight_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> new Weight(1.0, null));
	}

	@Test
	public void testEquality_Weight_TransitiveProperty() {
		Weight kg1 = new Weight(1.0, WeightUnit.KILOGRAM);
		Weight g1000 = new Weight(1000.0, WeightUnit.GRAM);
		Weight kg2 = new Weight(1.0, WeightUnit.KILOGRAM);

		assertTrue(kg1.equals(g1000));
		assertTrue(g1000.equals(kg2));
		assertTrue(kg1.equals(kg2));
	}

	@Test
	public void testEquality_Weight_ZeroValue() {
		assertTrue(new Weight(0.0, WeightUnit.KILOGRAM).equals(new Weight(0.0, WeightUnit.GRAM)));
	}

	@Test
	public void testEquality_Weight_NegativeWeight() {
		assertTrue(new Weight(-1.0, WeightUnit.KILOGRAM).equals(new Weight(-1000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testEquality_Weight_LargeValue() {
		assertTrue(new Weight(1000000.0, WeightUnit.GRAM).equals(new Weight(1000.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testEquality_Weight_SmallValue() {
		assertTrue(new Weight(0.001, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.GRAM)));
	}

	@Test
	public void testConversion_KilogramToGram() {
		double result = Weight.convert(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);
		assertEquals(1000.0, result, 0.01);
	}

	@Test
	public void testConversion_GramToKilogram() {
		double result = Weight.convert(1000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM);
		assertEquals(1.0, result, 0.01);
	}

	@Test
	public void testConversion_PoundToKilogram() {
		double result = Weight.convert(2.20462, WeightUnit.POUND, WeightUnit.KILOGRAM);
		assertEquals(1.0, result, 0.01);
	}

	@Test
	public void testConversion_KilogramToPound() {
		double result = Weight.convert(1.0, WeightUnit.KILOGRAM, WeightUnit.POUND);
		assertEquals(2.20, result, 0.01);
	}

	@Test
	public void testConversion_Weight_SameUnit() {
		Weight result = new Weight(5.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.KILOGRAM);
		assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testConversion_Weight_ZeroValue() {
		Weight result = new Weight(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
		assertEquals(new Weight(0.0, WeightUnit.GRAM), result);
	}

	@Test
	public void testConversion_Weight_NegativeValue() {
		Weight result = new Weight(-1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
		assertEquals(new Weight(-1000.0, WeightUnit.GRAM), result);
	}

	@Test
	public void testConversion_Weight_RoundTrip() {
		Weight original = new Weight(1.5, WeightUnit.KILOGRAM);
		Weight converted = original.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);
		assertTrue(original.equals(converted));
	}

	@Test
	public void testConversion_Weight_Infinite() {
		assertThrows(IllegalArgumentException.class,
				() -> Weight.convert(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM, WeightUnit.GRAM));
	}

	@Test
	public void testAddition_Weight_SameUnit_KilogramPlusKilogram() {
		Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_Weight_SameUnit_GramPlusGram() {
		Weight w1 = new Weight(500.0, WeightUnit.GRAM);
		Weight w2 = new Weight(300.0, WeightUnit.GRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(800.0, WeightUnit.GRAM), result);
	}

	@Test
	public void testAddition_Weight_CrossUnit_KilogramPlusGram() {
		Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_Weight_CrossUnit_GramPlusKilogram() {
		Weight w1 = new Weight(500.0, WeightUnit.GRAM);
		Weight w2 = new Weight(0.5, WeightUnit.KILOGRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(1000.0, WeightUnit.GRAM), result);
	}

	@Test
	public void testAddition_Weight_CrossUnit_PoundPlusKilogram() {
		Weight w1 = new Weight(2.20462, WeightUnit.POUND);
		Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

		Weight result = w1.add(w2);

		assertTrue(result.equals(new Weight(4.41, WeightUnit.POUND)));
	}

	@Test
	public void testAddition_Weight_ExplicitTargetUnit_Kilogram() {
		Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

		Weight result = w1.add(w2, WeightUnit.KILOGRAM);

		assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_Weight_ExplicitTargetUnit_Gram() {
		Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

		Weight result = w1.add(w2, WeightUnit.GRAM);

		assertEquals(new Weight(2000.0, WeightUnit.GRAM), result);
	}

	@Test
	public void testAddition_Weight_ExplicitTargetUnit_Pound() {
		Weight w1 = new Weight(1.0, WeightUnit.POUND);
		Weight w2 = new Weight(453.59, WeightUnit.GRAM);

		Weight result = w1.add(w2, WeightUnit.POUND);

		assertTrue(result.equals(new Weight(1.98, WeightUnit.POUND)));
	}

	@Test
	public void testAddition_Weight_Commutativity() {
		Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

		assertTrue(w1.add(w2).equals(w2.add(w1)));
	}

	@Test
	public void testAddition_Weight_WithZero() {
		Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(0.0, WeightUnit.GRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_Weight_NegativeValues() {
		Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(-2000.0, WeightUnit.GRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_Weight_NullSecondOperand() {
		Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);

		assertThrows(IllegalArgumentException.class, () -> w1.add(null));
	}

	@Test
	public void testAddition_Weight_LargeValues() {
		Weight w1 = new Weight(1e6, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1e6, WeightUnit.KILOGRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(2e6, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testWeightUnit_ConvertToBaseUnit() {
		assertEquals(1.0, WeightUnit.KILOGRAM.convertToBaseUnit(1.0), 1e-9);
		assertEquals(0.5, WeightUnit.GRAM.convertToBaseUnit(500.0), 1e-9);
		assertEquals(0.91, WeightUnit.POUND.convertToBaseUnit(2.0), 1e-2);
	}

	@Test
	public void testWeightUnit_ConvertFromBaseUnit() {
		assertEquals(1.0, WeightUnit.KILOGRAM.convertFromBaseUnit(1.0), 1e-9);
		assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), 1e-9);
		assertEquals(2.20, WeightUnit.POUND.convertFromBaseUnit(1.0), 1e-2);
	}
}
