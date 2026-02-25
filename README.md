## Quantity Measurement App – UC1 (Feet Equality)
### 📌 Overview
- This module checks whether two measurements given in feet are equal.
- It focuses on correct object equality, safe floating-point comparison, and clean OOP design.
### *⚙️ Use Case: UC1 – Feet Measurement Equality*
   - Accepts two numerical values in feet
   - Compares them for equality
   - Returns true if equal, otherwise false
### *⚙️ Key Implementation Points*
   - Uses a separate Feet class to represent a measurement
   - Measurement value is private and final (immutable)
   - equals() is overridden correctly
   - Double.compare() is used instead of ==
   - Handles null, type mismatch, and same reference cases safely#QunatityMeasurement

--------------------

## Quantity Measurement App – UC2 (Inches Equality)
*📌 Overview*
- This module checks whether two measurements given in inches are equal.
- It extends UC1 by supporting equality checks for inches while following the same design principles.
### ⚙️ Use Case: UC2 – Inches Measurement Equality
- Accepts two numerical values in inches
- Compares them for equality
- Returns true if equal, otherwise false
### ⚙️ Key Implementation Points
- Uses a separate Inches class to represent a measurement
- Measurement value is private and final (immutable)
- equals() is overridden correctly
- Double.compare() is used instead of ==
- Handles null, type mismatch, and same reference cases safely

-----------------------
## Quantity Measurement App – UC3 (Generic Length Equality)
*📌 Overview*
- This module checks whether two measurements given in inches and feets are equal.
### ⚙️ Use Case: UC3 – Generic Quantity Length 
- Accepts two numerical values along with their respective unit types
- Converts different units to a common base unit
- logic is centralized and reusable
- Measurement value and unit are encapsulated
- Handles:
   - null values
   - invalid units
   - same reference checks
   - type mismatch safely

------------------------------

## Quantity Measurement App – UC4 (Yard Equality)
*📌 Overview*
- This module checks whether two measurements given in yard ,inches ,centimeters and feets are equal.
### ⚙️ Use Case: UC4 – Yard Equality
- Accepts two numerical values along with their respective unit types
- Converts different units to a common base unit
- logic is centralized and reusable
- Measurement value and unit are encapsulated
- Handles:
   - null values
   - invalid units
   - conversion checks
   - type casting checks
----------------------------

## Quantity Measurement App – UC5 (Unit-to-Unit Conversion)
*📌 Overview*
- This module extends UC4 by adding explicit unit-to-unit conversion support to the Quantity Measurement App.
- Instead of only checking equality, the Length API now allows converting a measurement from one unit to another using centralised conversion factors.
- Supports conversion across feet ↔ inches ↔ yards ↔ centimeters.
### ⚙️ Use Case: UC5 – Unit-to-Unit Conversion 
- Accepts a numerical value along with a source unit and a target unit
- Supports conversion between all supported length units
- Normalises values using a common base unit
- Converts the normalised value into the target unit
- Returns the converted numeric value
### ⚙️ Key Implementation Points
- Continues using the same immutable Length class
- Reuses the existing LengthUnit enum with predefined conversion factors
- Conversion logic is centralised and consistent
- Supports both:
   - Static conversion using raw values
   - Instance-level conversion using convertTo()
- Validation ensures:
   - Units are non-null and valid
   - Values are finite (not NaN or infinite)
   - Conversion preserves mathematical accuracy within floating-point tolerance
   - No mutation of existing objects; conversions return new values or instances

-------------------------------

## Quantity Measurement App – UC6 (Unit-Addition Conversion)
*📌 Overview*
- This module enables addition operations between two length measurements.
- It supports adding lengths in the same or different units (within the length category) and returns the result in the unit of the first operand.
- For example, adding 1 foot and 12 inches yields 2 feet.
### ⚙️ Use Case: UC5 – Addition of Two Length Units (potentially different units)
- Accepts two numerical values with their respective units.
- Adds them and returns the sum in the unit of the first operand.
### ⚙️ Key Implementation Points
- Addition of value objects with unit conversion.
- Immutability and safe handling of operands.
- Normalisation to a base unit for accurate arithmetic.
- Floating-point precision management.
- Commutativity and identity element behaviour.
- Robust validation for null or invalid inputs.

-----------------------------

## Quantity Measurement App – UC7 (Addition with Target Unit Specification)
📌 Overview
- This module extends UC6 by allowing the caller to explicitly specify a target unit for addition results.
- Instead of defaulting to the first operand’s unit, the result can be returned in any supported unit.
- Example: 1 foot + 12 inches with target unit YARDS ≈ 0.667 yards.
### ⚙️ Use Case: UC7 – Addition with Target Unit Specification
- Accepts two numerical values with their respective units and a target unit.
- Adds them and returns the sum in the explicitly specified target unit.
### ⚙️ Key Implementation Points (UC7 – Explicit Target Unit Addition)
- Uses the same immutable Length class and LengthUnit enum.
- Overloaded add() method:
- UC6: add(A, B) → result in the first operand’s unit.
- UC7: add(A, B, targetUnit) → result in explicitly specified unit.
- Private utility method handles conversion → addition → target unit conversion.
- Validation added: target unit must be non-null and valid.
- Preserves immutability, precision, and commutativity.
- Maintains backward compatibility with the UC6 addition.

--------------------------------
## Quantity Measurement App – UC8 (Standalone LengthUnit Refactoring)
*📌 Overview*
- This module refactors the LengthUnit enum to a standalone, top-level class with full responsibility for unit conversions.
- QuantityLength is simplified to focus on value comparison and arithmetic, delegating all conversion logic to LengthUnit.
- The change improves cohesion, eliminates circular dependencies, and establishes a scalable pattern for multiple measurement categories.
### ⚙️ Use Case: UC8 – Refactoring Unit Enum to Standalone with Conversion Responsibility
- LengthUnit manages all conversion logic (to/from base unit).
- QuantityLength handles equality, addition, and arithmetic only.
- Supports all functionality from UC1–UC7 without modifying client code.
### ⚙️ Key Implementation Points
- LengthUnit handles all unit conversion logic.
- QuantityLength delegates conversions → focuses on comparisons/addition.
- Methods:
   - convertToBaseUnit(double value)
   - convertFromBaseUnit(double baseValue)

-----------------
## Quantity Measurement App - UC9 (Weight Measurement)
- Description: UC9 extends the Quantity Measurement App to support weight measurements (kilogram, gram, pound) alongside - - length. Weight operations mirror length operations: equality, conversion, and addition. Weight and length are separate, type-safe categories.

### Units & Conversion:
   - KILOGRAM (kg) – base unit
   - GRAM (g) – 1 kg = 1000 g
   - POUND (lb) – 1 lb ≈ 0.453592 kg
### Example:

- Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM)) → true
- Quantity(2.20462, POUND).convertTo(KILOGRAM) → Quantity(~1.0, KILOGRAM)
- Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM), GRAM) → Quantity(2000.0, GRAM)
### Implementation:

- WeightUnit enum handles all conversion logic.
- QuantityWeight class handles equality and arithmetic, delegating conversions to WeightUnit.
- Supports cross-unit equality and addition, explicit target unit, and immutable objects.
- Round-trip conversions maintain precision using epsilon.
- Weight vs. length comparisons are not allowed.

-----------------------
## Quantity Measurement App – UC10 (Generic Quantity Class with Unit Interface)
*📌 Overview*
- This module refactors the previous category-specific Quantity classes into a single, generic Quantity<U> class that works with any measurement category implementing the IMeasurable interface.
- It eliminates code duplication, simplifies demonstration methods, and ensures type-safe operations across multiple measurement categories like length and weight.
### ⚙️ Use Case: UC10 – Generic Quantity and Multi-Category Support
- Accepts two numerical values with their respective units
- Supports equality comparison, unit conversion, and addition
- Prevents invalid cross-category comparisons (e.g., length vs. weight)
- Returns a new Quantity object for conversion or addition; equality returns a boolean
### ⚙️ Key Implementation Points
- Uses a single generic class: Quantity<U extends IMeasurable>
- Holds private final fields: value and unit (immutable)
- IMeasurable interface standardises unit behaviour across categories
- Enums (LengthUnit, WeightUnit) implement IMeasurable and encapsulate conversion logic
- equals() compares base unit values using Double.compare() and validates unit types
- convertTo(U targetUnit) delegates to the unit’s conversion methods and returns new instance
- add(Quantity<U> other) and add(Quantity<U> other, U targetUnit) perform arithmetic safely
-----------------------------
## ⚙️ Use Case: UC11 – Volume Measurement Equality, Conversion, and Addition
- Accepts numerical values with their respective volume units (LITRE, MILLILITRE, GALLON)
- Compares volumes for equality
- Converts between volume units
- Adds two volume quantities, optionally specifying a target unit
### ⚙️ Key Implementation Points
- VolumeUnit enum implements IMeasurable with LITRE as the base unit
- Conversion factors: MILLILITRE = 0.001 L, GALLON ≈ 3.78541 L
- Equality uses base unit comparison with epsilon tolerance
- Generic Quantity<U> handles conversion and addition without modification
- Maintains type safety: volume cannot be mixed with length or weight
- Objects are immutable; addition and conversion return new instances

### UC12: Subtraction and Division - Expanding Arithmetic Operations
### What we did:
- Implemented subtract() method with same/explicit target unit
- Implemented divide() method returning ratio
- Added comprehensive validation for all arithmetic operations
- Centralized validation logic to avoid duplication
### What we learned:
- Consistent API design: Subtraction mirrors addition's dual-method pattern
- Division semantics: Returns scalar (double), not Quantity
- Validation patterns: Consistent error handling across operations
- Edge case handling: Division by zero protection with epsilon comparison
### Key implementations:
```
Subtraction:
Quantity<LengthUnit> l1 = new Quantity<>(5.0, FEET);
Quantity<LengthUnit> l2 = new Quantity<>(3.0, FEET);
Quantity<LengthUnit> diff = l1.subtract(l2); // 2.0 FEET

// With explicit target unit
Quantity<LengthUnit> diffInches = l1.subtract(l2, INCHES); // 24.0 INCHES
Division:
Quantity<LengthUnit> l1 = new Quantity<>(6.0, FEET);
Quantity<LengthUnit> l2 = new Quantity<>(3.0, FEET);
double ratio = l1.divide(l2); // 2.0 (dimensionless)
```
---------------------------
## UC13: Centralized Arithmetic Operations Using Enum Strategy
- Description: UC14 refactors the Quantity Measurement App by introducing an ArithmeticOperation enum (ADD, SUBTRACT, DIVIDE) to centralize arithmetic behavior. All operations now delegate to a single private helper method, eliminating duplicate validation and conversion logic while preserving existing functionality.
### Implementation:
- ArithmeticOperation enum handles operation-specific computation.
- Private helper method performs validation, base unit conversion, enum dispatch, and result conversion.
- Add and subtract results are rounded to two decimals.
- Divide returns a dimensionless raw double value.
- No changes required to existing unit enums (LengthUnit, WeightUnit, VolumeUnit).
- Full backward compatibility with UC12 maintained.
### Example:
- Quantity(10.0, FEET).add(Quantity(5.0, FEET)) → 15.00 FEET
- Quantity(10.0, FEET).subtract(Quantity(5.0, FEET)) → 5.00 FEET
- Quantity(10.0, FEET).divide(Quantity(5.0, FEET)) → 2.0
