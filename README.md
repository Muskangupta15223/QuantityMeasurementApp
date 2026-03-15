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
-----------------------------------
## UC14: Temperature Measurements - Selective Arithmetic Support
### What we did:
- Created TemperatureUnit enum (CELSIUS, FAHRENHEIT, KELVIN)
- Implemented non-linear conversions (temperature has offset, not just scaling)
- Created SupportsArithmetic functional interface
- Extended IMeasurable with arithmetic support checking
- Made arithmetic operations validate unit support
- Key insight: Temperature doesn't support addition/subtraction of absolute values

```
Example
Functional Interface for Arithmetic Support
@FunctionalInterface
public interface SupportsArithmetic {
    boolean isSupported();
}
```
----------

## **UC15: N-Tier Architecture Refactoring**

### **What we did:**
- Refactored the monolithic application into a layered N-Tier Architecture
- Created **Controller**, **Service**, **Repository**, **Model/DTO**, and **Exception** layers
- Organized code into proper package structure reflecting the architecture
- Implemented design patterns: Singleton, Factory, Dependency Injection
- Added file-based persistence through cache repository
- Created clean separation between internal models and external DTOs

### **What we learned:**

#### 1. N-Tier Architecture Pattern
```
📂 controller/
    └── QuantityMeasurementController.java
📂 service/
    ├── IQuantityMeasurementService.java
    └── QuantityMeasurementServiceImpl.java
📂 repository/
    ├── IQuantityMeasurementRepository.java
    └── QuantityMeasurementCacheRepository.java
📂 model/
    ├── QuantityModel.java
    └── QuantityMeasurementEntity.java
📂 dto/
    └── QuantityDTO.java
📂 exception/
    └── QuantityMeasurementException.java
```

**Layer responsibilities:**
- **Controller**: User interaction layer, delegates to service
- **Service**: Business logic, orchestrates operations
- **Repository**: Data persistence, abstracts storage mechanism
- **Model/Entity**: Internal domain objects, persistence entities
- **DTO**: External API contracts, decoupled from domain

#### 2. Data Transfer Object (DTO) Pattern
```java
public class QuantityDTO {
    public interface IMeasurableUnit {
        String getUnitName();
        String getMeasurementType();
    }
    
    public enum LengthUnit implements IMeasurableUnit { FEET, INCHES, YARDS, CENTIMETERS; }
    public enum VolumeUnit implements IMeasurableUnit { LITRE, MILLILITRE, GALLON; }
    public enum WeightUnit implements IMeasurableUnit { KILOGRAM, GRAM, POUND; }
    public enum TemperatureUnit implements IMeasurableUnit { CELSIUS, FAHRENHEIT, KELVIN; }
    
    public double value;
    public String unit;
    public String measurementType;
}
```

**Why DTO matters:**
- **Decoupling**: External API independent of internal implementation
- **Versioning**: Can change internal model without breaking API
- **Security**: Expose only what's needed externally
- **Simplicity**: Simpler objects for data transfer

#### 3. Repository Pattern with Singleton
```java
public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    private static QuantityMeasurementCacheRepository instance;
    private final Map<String, QuantityMeasurementEntity> cache;
    private static final Path STORAGE_FILE = Path.of("quantity_measurements.dat");
    
    private QuantityMeasurementCacheRepository() {
        cache = new ConcurrentHashMap<>();
        loadFromDisk();
    }
    
    public static synchronized QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }
}
```

**Singleton benefits:**
- Single instance ensures data consistency
- Central access point for persistence
- Lazy initialization for resource efficiency
- Thread-safe implementation with `synchronized`

#### 4. Service Layer with Interface Segregation
```java
public interface IQuantityMeasurementService {
    boolean compare(QuantityDTO dto1, QuantityDTO dto2);
    QuantityDTO convert(QuantityDTO dto, String targetUnit);
    QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2);
    QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2, String targetUnit);
    QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2);
    QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2, String targetUnit);
    double divide(QuantityDTO dto1, QuantityDTO dto2);
}

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repository;
    
    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;  // Dependency Injection
    }
}
```

**Interface Segregation Principle:**
- Clients depend only on methods they use
- Repository interface separate from service interface
- Easy to mock for testing

#### 5. Factory Pattern in Application
```java
public final class QuantityMeasurementApp {
    private static QuantityMeasurementApp instance;
    private final QuantityMeasurementController controller;
    
    private QuantityMeasurementApp() {
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        this.controller = new QuantityMeasurementController(service, repository);
    }
    
    // Factory methods
    public static Quantity<LengthUnit> createLength(double value, LengthUnit unit) {
        return new Quantity<>(value, unit);
    }
}
```

**Factory pattern benefits:**
- Centralized object creation
- Encapsulates construction complexity
- Easy to change implementations

#### 6. Entity Pattern for Persistence
```java
public class QuantityMeasurementEntity implements Serializable {
    private final String id;
    private final String operationType;
    private final double value1;
    private final String unit1;
    private final double value2;
    private final String unit2;
    private final String resultUnit;
    private final double result;
    private final LocalDateTime timestamp;
}
```

**Entity characteristics:**
- Serializable for persistence
- Unique ID for identification
- Immutable for thread safety
- Complete operation record

#### 7. Controller Layer
```java
public class QuantityMeasurementController {
    private final IQuantityMeasurementService service;
    private final IQuantityMeasurementRepository repository;
    
    public boolean compareQuantities(QuantityDTO dto1, QuantityDTO dto2) {
        return service.compare(dto1, dto2);
    }
    
    public QuantityDTO convertQuantity(QuantityDTO dto, String targetUnit) {
        return service.convert(dto, targetUnit);
    }
}
```

**Controller responsibilities:**
- Receive user requests
- Delegate to appropriate service
- Return results to caller
- No business logic

### **Design patterns summary:**

| Pattern | Implementation | Purpose |
|---------|----------------|---------|
| **Singleton** | CacheRepository, App | Single instance, global access |
| **Factory** | QuantityMeasurementApp | Object creation encapsulation |
| **Repository** | IQuantityMeasurementRepository | Data access abstraction |
| **DTO** | QuantityDTO | External data transfer |
| **Dependency Injection** | ServiceImpl constructor | Loose coupling |
| **Interface Segregation** | IService, IRepository | Client-specific interfaces |

-------------
## **UC16: Database Integration with JDBC for Quantity Measurement Persistence**

### Implementaion
- Added JDBC-based persistent repository: `QuantityMeasurementDatabaseRepository`
- Added connection pooling with HikariCP (`ConnectionPool`)
- Added centralized configuration via `application.properties` (`ApplicationConfig`)
- Added production schema script at `src/main/resources/db/schema.sql`
- Extended repository contract for query/filter/count/delete/resource operations
- Kept backward compatibility with `QuantityMeasurementCacheRepository`
- Wired repository selection through configuration (`app.repository.type=cache|database`)

### **What we learned:**

#### 1. Repository Swapping with Dependency Injection
```java
String repositoryType = ApplicationConfig.getInstance().getRepositoryType();
if ("database".equalsIgnoreCase(repositoryType)) {
    return new QuantityMeasurementDatabaseRepository();
}
return QuantityMeasurementCacheRepository.getInstance();
```

#### 2. JDBC + Parameterized SQL
```java
PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
statement.setDouble(1, entity.getThisValue());
statement.setString(2, entity.getThisUnit());
statement.setString(3, entity.getThisMeasurementType());
```

#### 3. Transaction Management
```java
connection.setAutoCommit(false);
deleteHistory.executeUpdate();
deleteAll.executeUpdate();
connection.commit();
```

#### 4. Connection Pool Monitoring
```java
Map<String, Integer> stats = repository.getPoolStatistics();
// activeConnections, idleConnections, totalConnections, threadsAwaitingConnection
```
-------------