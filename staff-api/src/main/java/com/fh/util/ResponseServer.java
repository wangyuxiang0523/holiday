package com.fh.util;

public class ResponseServer {
    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public Object getData() {
        return data;
    }

    private ResponseServer(Integer code, String message, Object data){
        this.code= code;
        this.message=message;
        this.data= data;
    }
    private ResponseServer(Integer code, String message){
        this.code= code;
        this.message=message;
    }
    public static ResponseServer success(){
        return  new ResponseServer(ServerEnum.SUCCESS.getCode(),ServerEnum.SUCCESS.getMessage());
    }
    public static ResponseServer success(Object data){
        return  new ResponseServer(ServerEnum.SUCCESS.getCode(),ServerEnum.SUCCESS.getMessage(),data);
    }
    public static ResponseServer error(){
        return new ResponseServer(ServerEnum.ERROR.getCode(),ServerEnum.ERROR.getMessage());
    }
    public static ResponseServer error(ServerEnum serverEnum){
        return new ResponseServer(serverEnum.getCode(),serverEnum.getMessage());
    }
}
