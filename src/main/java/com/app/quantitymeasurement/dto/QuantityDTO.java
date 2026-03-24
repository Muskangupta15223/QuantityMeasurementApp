package com.app.quantitymeasurement.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "A Quantity with a value and unit")
public class QuantityDTO {
	
	private static final Logger logger = LoggerFactory.getLogger(QuantityDTO.class);
    public interface IMeasurableUnit {
        String getUnitName();
        String getMeasurementType();
    }

    public enum LengthUnit implements IMeasurableUnit {
        FEET, INCHES, YARDS, CENTIMETERS;

        @Override
        public String getUnitName() {
            return this.name();
        }

        @Override
        public String getMeasurementType() {
            return this.getClass().getSimpleName();
        }
    }

    public enum VolumeUnit implements IMeasurableUnit {
        LITRE, MILLILITRE, GALLON;

        @Override
        public String getUnitName() {
            return this.name();
        }

        @Override
        public String getMeasurementType() {
            return this.getClass().getSimpleName();
        }
    }

    public enum WeightUnit implements IMeasurableUnit {
        KILOGRAM, GRAM, POUND;

        @Override
        public String getUnitName() {
            return this.name();
        }

        @Override
        public String getMeasurementType() {
            return this.getClass().getSimpleName();
        }
    }

    public enum TemperatureUnit implements IMeasurableUnit {
        CELSIUS, FAHRENHEIT, KELVIN;

        @Override
        public String getUnitName() {
            return this.name();
        }

        @Override
        public String getMeasurementType() {
            return this.getClass().getSimpleName();
        }
    }

    @NotNull(message = "Value can't be empty")
    @Schema(example = "1.0")
    private double value;
    @NotNull(message = "Unit cannot be null")
    @Schema(example = "FEET", allowableValues = {
    		"FEET", "INCHES", "YARDS","CENTIMETERS",
    		"LITRE", "MILLILITRE", "GRAM", "KILOGRAM",
    		"POUND","CELSIUS","FAHRENHEIT","KELVIN"
    })
    private String unit;
    @NotNull
    @Pattern(
    	    regexp = "^(LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit)$",
    	    message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"
    	)
    @Schema(example = "LengthUnit", allowableValues = {
    		"LengthUnit","VolumeUnit","WeightUnit","TemperatureUnit"
    })
    private String measurementType;

    public QuantityDTO(double value, IMeasurableUnit unit) {
        this.value = value;
        this.unit = unit.getUnitName();
        this.measurementType = unit.getMeasurementType();
    }

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }
    @AssertTrue(message = "Unit must be valid")
    public boolean isValidUnit() {
    	logger.info("Validating unit :" + unit + "for measurement type : "+ measurementType);
    	try {
    		switch(measurementType) {
    		case "LengthUnit":
    			LengthUnit.valueOf(unit.toUpperCase());
    			break;
    		case "VolumeUnit":
    			VolumeUnit.valueOf(unit.toUpperCase());
    			break;
    		case "TemperatureUnit":
    			TemperatureUnit.valueOf(unit.toUpperCase());
    			break;
    		case "WeightUnit":
    			WeightUnit.valueOf(unit.toUpperCase());
    			break;
    		default :
    			return false;
    		}
    	}catch(IllegalArgumentException e) {
    			return false;
    	   }
    	return true;
    }
}