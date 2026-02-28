## Quantity Measurement App

- Building a Quantity Measurement System
- This document walks through the evolution of the Quantity Measurement codebase, where we progressively learned fundamental software design principles by solving increasingly complex problems. From basic equality comparisons to advanced arithmetic operations with selective support, this journey demonstrates real-world software evolution.
- 
------------
### Key Software Engineering Principles Learned

| Principle               | UC Stage              | How Implemented |
|-------------------------|-----------------------|-----------------|
| Value Objects           | UC1                   | Immutable objects representing measurements |
| DRY                     | UC3, UC10, UC13       | Generic classes and centralized operations eliminate duplication |
| Enums as Constants      | UC3                   | Type-safe unit representation |
| Separation of Concerns  | UC8                   | Units handle conversion, Quantity handles operations |
| Single Responsibility   | UC10                  | Each class has ONE clear purpose |
| Open-Closed Principle   | UC4, UC10, UC11       | Add features without modifying existing code |
| Liskov Substitution     | UC10, UC14            | Any `IMeasurable` works with `Quantity` (with constraints) |
| Interface Segregation   | UC10                  | Minimal, focused `IMeasurable` interface |
| Dependency Inversion    | UC10                  | Depend on abstraction (`IMeasurable`), not concrete types |
| Generics                | UC10                  | Type-safe polymorphism |
| Composition over Inheritance | UC3, UC10      | `Quantity` HAS-A unit, not IS-A specific type |
| Strategy Pattern        | UC13                  | `ArithmeticOperation` enum with lambda operations |
| Template Method         | UC13, UC14            | Shared validation + operation flow with override points |
| Functional Programming  | UC13, UC14            | Lambdas, `DoubleBinaryOperator`, `Function<T,R>` |
| Fail-Fast Principle     | UC12, UC14            | Validate before executing operations |
| Precision Management    | UC11                  | Rounding strategies for floating-point arithmetic |
| Selective Constraints   | UC14                  | Supporting different operations for different types |

--------------------------------------------




