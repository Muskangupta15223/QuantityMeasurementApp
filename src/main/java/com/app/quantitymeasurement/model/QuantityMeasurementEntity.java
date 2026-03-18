package com.app.quantitymeasurement.model;

import com.app.quantitymeasurement.unit.IMeasurable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "quantity_measurements",
        indexes = {
                @Index(name = "idx_operation", columnList = "operation"),
                @Index(name = "idx_measurement_type", columnList = "this_measurement_type, that_measurement_type")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // THIS
    @Column(name = "this_value")
    private double thisValue;

    @Column(name = "this_unit")
    private String thisUnit;

    @Column(name = "this_measurement_type")
    private String thisMeasurementType;

    // THAT
    @Column(name = "that_value")
    private double thatValue;

    @Column(name = "that_unit")
    private String thatUnit;

    @Column(name = "that_measurement_type")
    private String thatMeasurementType;

    // OPERATION
    @Column(name = "operation")
    private String operation;

    // RESULT
    @Column(name = "result_value")
    private double resultValue;

    @Column(name = "result_unit")
    private String resultUnit;

    @Column(name = "result_measurement_type")
    private String resultMeasurementType;

    @Column(name = "result_string")
    private String resultString;

    // ERROR HANDLING
    @Column(name = "is_error")
    private boolean isError;

    @Column(name = "error_message")
    private String errorMessage;

    //  CONSTRUCTORS 
    
    public QuantityMeasurementEntity(
            QuantityModel<? extends IMeasurable> thisQuantity,
            QuantityModel<? extends IMeasurable> thatQuantity,
            String operation,
            String result
    ) {
        this(thisQuantity, thatQuantity, operation);
        this.resultString = result;
    }

    public QuantityMeasurementEntity(
            QuantityModel<? extends IMeasurable> thisQuantity,
            QuantityModel<? extends IMeasurable> thatQuantity,
            String operation,
            QuantityModel<? extends IMeasurable> result
    ) {
        this(thisQuantity, thatQuantity, operation);
        this.resultValue = result.getValue();
        this.resultUnit = result.getUnit().getUnitName();
        this.resultMeasurementType = result.getUnit().getMeasurementType();
    }

    public QuantityMeasurementEntity(
            QuantityModel<? extends IMeasurable> thisQuantity,
            QuantityModel<? extends IMeasurable> thatQuantity,
            String operation,
            String errorMessage,
            boolean isError
    ) {
        this(thisQuantity, thatQuantity, operation);
        this.errorMessage = errorMessage;
        this.isError = isError;
    }

    private QuantityMeasurementEntity(
            QuantityModel<? extends IMeasurable> thisQuantity,
            QuantityModel<? extends IMeasurable> thatQuantity,
            String operation
    ) {
        if (thisQuantity != null) {
            this.thisValue = thisQuantity.getValue();
            this.thisUnit = thisQuantity.getUnit().getUnitName();
            this.thisMeasurementType = thisQuantity.getUnit().getMeasurementType();
        }

        if (thatQuantity != null) {
            this.thatValue = thatQuantity.getValue();
            this.thatUnit = thatQuantity.getUnit().getUnitName();
            this.thatMeasurementType = thatQuantity.getUnit().getMeasurementType();
        }

        this.operation = operation;
    }

    // ================= LIFECYCLE CALLBACK =================

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}