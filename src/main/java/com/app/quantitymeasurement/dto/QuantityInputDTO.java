package com.app.quantitymeasurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityInputDTO {

    @NotNull
    @Valid
    private QuantityDTO thisQuantityDTO;

    @NotNull
    @Valid
    private QuantityDTO thatQuantityDTO;
}