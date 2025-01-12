package com.munaf.ERP_SYSTEM.advices;

import com.munaf.ERP_SYSTEM.exceptions.InvalidInputException;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseModel internalServerErrorHandler(Exception exception){
        return CommonResponse.BAD_REQUEST(exception.getLocalizedMessage());
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseModel handleResourceAlreadyExists(ResourceAlreadyExists e){
        return CommonResponse.BAD_REQUEST(e.getLocalizedMessage());
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseModel handleResourceNotFound(ResourceNotFound e){
        return CommonResponse.NOT_FOUND(e.getLocalizedMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseModel handleInvalidInput(InvalidInputException e){
        return CommonResponse.BAD_REQUEST(e.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseModel handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return CommonResponse.BAD_REQUEST(errors);
    }

}
