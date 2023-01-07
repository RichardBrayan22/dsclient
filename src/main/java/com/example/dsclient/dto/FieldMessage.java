package com.example.dsclient.dto;

public class FieldMessage {

    private String fieldName;
    private String message;

    //#region Construtores
    public FieldMessage(String fieldName, String message){
        this.fieldName = fieldName;
        this.message = message;
    }
    //#endregion

    //#region
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //#endregion
    
    
}
