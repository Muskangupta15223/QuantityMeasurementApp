package com.app.quantitymeasurement.dto;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementDTO {
	   private double thisValue;
	    private String thisUnit;
	    private String thisMeasurementType;
	    private double thatValue;
	    private String thatUnit;
	    private String thatMeasurementType;
	    private String operation;
	    private String resultString;
	    private double resultValue;
	    private String errorMessage;
	    private boolean error;

	    public static QuantityMeasurementDTO fromEntity(QuantityMeasurementEntity entity) {
	        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
	        dto.setThisValue(entity.getThisValue());
	        dto.setThisUnit(entity.getThisUnit());
	        dto.setThisMeasurementType(entity.getThisMeasurementType());
	        dto.setThatValue(entity.getThatValue());
	        dto.setThatUnit(entity.getThatUnit());
	        dto.setThatMeasurementType(entity.getThatMeasurementType());
	        dto.setOperation(entity.getOperation());
	        dto.setResultValue(entity.getResultValue());
	        dto.setErrorMessage(entity.getErrorMessage());
	        return dto;
	    }

	    public QuantityMeasurementEntity toEntity() {
	        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
	        entity.setThisValue(this.thisValue);
	        entity.setThisUnit(this.thisUnit);
	        entity.setThisMeasurementType(this.thisMeasurementType);
	        entity.setThatValue(this.thatValue);
	        entity.setThatUnit(this.thatUnit);
	        entity.setThatMeasurementType(this.thatMeasurementType);
	        entity.setOperation(this.operation);
	        entity.setResultValue(this.resultValue);
	        entity.setErrorMessage(this.errorMessage);
	        return entity;
	    }

	    public static List<QuantityMeasurementDTO> fromEntityList(List<QuantityMeasurementEntity> entities) {
	        return entities.stream()
	                .map(QuantityMeasurementDTO::fromEntity)
	                .collect(Collectors.toList());
	    }

	    public static List<QuantityMeasurementEntity> toEntityList(List<QuantityMeasurementDTO> dtos) {
	        return dtos.stream()
	                .map(QuantityMeasurementDTO::toEntity)
	                .collect(Collectors.toList());
	    }
	}