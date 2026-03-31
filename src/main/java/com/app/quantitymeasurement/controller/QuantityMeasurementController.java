package com.app.quantitymeasurement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementController.class);

    @Autowired
    private IQuantityMeasurementService service;
    @Autowired
    private UserRepository userRepository;

    private User getUser(Authentication authentication) {
        if (authentication == null) return null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof String) {
            String email = (String) principal;
            return userRepository.findByEmail(email).orElse(null);
        }
        return null;
    }

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities")
    public ResponseEntity<QuantityMeasurementDTO> compareQuantities(
            @Valid @RequestBody QuantityInputDTO input,
            Authentication authentication) {

        User user = getUser(authentication);
        logger.info("POST /compare by {}", user != null ? user.getEmail() : "anonymous");

        QuantityMeasurementDTO result = service.compareQuantities(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO(),
                user
        );
        
        return ResponseEntity.ok(result);
    }

    @PostMapping("/convert")
    @Operation(summary = "Convert a quantity to a target unit")
    public ResponseEntity<QuantityMeasurementDTO> convertQuantity(@Valid @RequestBody QuantityInputDTO input,Authentication authentication) {

        User user = getUser(authentication);
        logger.info("POST /convert by {}", user != null ? user.getEmail() : "anonymous");

        QuantityMeasurementDTO result = service.convertQuantity(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO(),
                user
        );

        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public ResponseEntity<QuantityMeasurementDTO> addQuantities(
            @Valid @RequestBody QuantityInputDTO input,
            Authentication authentication) {

        User user = getUser(authentication);
        logger.info("POST /add by {}", user != null ? user.getEmail() : "anonymous");

        QuantityMeasurementDTO result = service.addQuantities(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO(),
                user
        );

        return ResponseEntity.ok(result);
    }

    @PostMapping("/subtract")
    @Operation(summary = "Subtract two quantities")
    public ResponseEntity<QuantityMeasurementDTO> subtractQuantities(
            @Valid @RequestBody QuantityInputDTO input,
            Authentication authentication) {

        User user = getUser(authentication);
        logger.info("POST /subtract by {}", user != null ? user.getEmail() : "anonymous");

        QuantityMeasurementDTO result = service.subtractQuantities(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO(),
                user
        );

        return ResponseEntity.ok(result);
    }

    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities")
    public ResponseEntity<QuantityMeasurementDTO> divideQuantities(
            @Valid @RequestBody QuantityInputDTO input,
            Authentication authentication) {

        User user = getUser(authentication);
        logger.info("POST /divide by {}", user != null ? user.getEmail() : "anonymous");

        QuantityMeasurementDTO result = service.divideQuantities(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO(),
                user
        );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(@PathVariable String operation) {
        return ResponseEntity.ok(service.getHistoryByOperation(operation));
    }

    @GetMapping("/history/type/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getMeasurementTypeHistory(@PathVariable String measurementType) {
        return ResponseEntity.ok(service.getHistoryByMeasurementType(measurementType));
    }

    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation) {
        return ResponseEntity.ok(service.getCountByOperation(operation));
    }

    @GetMapping("/history/errored")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {
        return ResponseEntity.ok(service.getErrorHistory());
    }

    // 🔥 USER-SPECIFIC APIs

    @GetMapping("/my/history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getMyHistory(Authentication authentication) {
        User user = getUser(authentication);
        return ResponseEntity.ok(service.getUserHistory(user));
    }

    @GetMapping("/my/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getMyOperationHistory(
            @PathVariable String operation,
            Authentication authentication) {

        User user = getUser(authentication);
        return ResponseEntity.ok(service.getUserHistoryByOperation(user, operation));
    }

    @GetMapping("/my/history/type/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getMyTypeHistory(
            @PathVariable String measurementType,
            Authentication authentication) {

        User user = getUser(authentication);
        return ResponseEntity.ok(service.getUserHistoryByMeasurementType(user, measurementType));
    }

    @GetMapping("/my/count/{operation}")
    public ResponseEntity<Long> getMyCount(
            @PathVariable String operation,
            Authentication authentication) {

        User user = getUser(authentication);
        return ResponseEntity.ok(service.getUserCountByOperation(user, operation));
    }

    @GetMapping("/my/history/errored")
    public ResponseEntity<List<QuantityMeasurementDTO>> getMyErrors(Authentication authentication) {
        User user = getUser(authentication);
        return ResponseEntity.ok(service.getUserErrorHistory(user));
    }
}