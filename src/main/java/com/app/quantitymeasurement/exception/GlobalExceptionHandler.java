package com.app.quantitymeasurement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
class ErrorResponse {

    public LocalDateTime timestamp;
    public int status;
    public String error;
    public String message;
    public String path;
}
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            Logger.getLogger(GlobalExceptionHandler.class.getName());

    // ================= VALIDATION EXCEPTION =================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        logger.warning(String.format("The Exception is %s", ex.getMessage()));

        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        List<String> errMsg = errorList.stream()
                .map(objErr -> objErr.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse();
        error.timestamp = LocalDateTime.now();
        error.status = HttpStatus.BAD_REQUEST.value();
        error.error = "Quantity Measurement Error";
        error.message = String.join(", ", errMsg);

        logger.warning("Handling validation exception: " + error.message);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // ================= CUSTOM EXCEPTION =================

    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<ErrorResponse> handleQuantityMeasurementException(
            QuantityMeasurementException ex,
            WebRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.timestamp = LocalDateTime.now();
        error.status = HttpStatus.BAD_REQUEST.value();
        error.error = "Quantity Measurement Error";
        error.message = ex.getMessage();
        error.path = request.getDescription(false).replace("uri=", "");

        logger.warning("Handling QuantityMeasurementException: "
                + ex.getMessage() + " for request path: " + error.path);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // ================= GLOBAL EXCEPTION =================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.timestamp = LocalDateTime.now();
        error.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        error.error = "Internal Server Error";
        error.message = ex.getMessage();
        error.path = request.getDescription(false).replace("uri=", "");

        logger.severe("Handling global exception: "
                + ex.getMessage() + " for request path: " + error.path);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}