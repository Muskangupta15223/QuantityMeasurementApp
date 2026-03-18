package com.app.quantitymeasurement.dto;

public enum OperationType {
    COMPARE,
    CONVERT,
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;
    
    public String getDisplayName() {
    	return this.name().toLowerCase();
    }
}
