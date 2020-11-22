package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class InternalServerErrorException extends GeneralException{
    private static final long serialVersionUID=1L;

    public InternalServerErrorException(String code, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalServerErrorException(String code, ErrorDto data, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message,Arrays.asList(data));
    }
}
