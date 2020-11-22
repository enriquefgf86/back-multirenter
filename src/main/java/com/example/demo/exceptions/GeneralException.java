package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;

public class GeneralException extends Exception{
    private static final long serialVersionUID=1L;
    private final String code;
    private final int responseCode;
    private List<ErrorDto> errorDtoList=new ArrayList<>();


    public GeneralException(String code, int responseCode,String message) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
    }

    public GeneralException(String code, int responseCode,String message, List<ErrorDto> errorDtoList) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
        this.errorDtoList = errorDtoList;
        this.errorDtoList.addAll(errorDtoList);

    }

     /////////getters y setters//////
    public String getCode() {   return code;  }
    public int getResponseCode() {   return responseCode; }

    public List<ErrorDto> getErrorDtoList() {  return errorDtoList;}
    public void setErrorDtoList(List<ErrorDto> errorDtoList) {    this.errorDtoList = errorDtoList;  }
}
