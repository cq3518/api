package com.winning.finance.apitool.base;

import java.io.Serializable;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/25 15:19
 */
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = 6054052582421291408L;

    private String message;
    private Object data;
    private String code;
    private boolean success;
    private Long total;

    public ResponseResult(){}

    public ResponseResult(Object data) {
        this.code= ResultCode.SUCCESS.getCode();
        this.success=true;
        this.data = data;
    }
    public ResponseResult(Object data,Long total) {
        this.code= ResultCode.SUCCESS.getCode();
        this.data = data;
        this.success=true;
        this.total = total;
    }


    public String getMessage() {
        return message;
    }

    public ResponseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean getSuccess() {
        return success;
    }

    public ResponseResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseResult setCode(String code) {
        this.code = code;
        return this;
    }

    public Long getTotal() {
        return this.total;
    }

    public ResponseResult setTotal(Long total) {
        this.total = total;
        return this;
    }

    public static ResponseResult success(Object data){
        return new ResponseResult(data);
    }

    public static ResponseResult success(Object data,Long total){

        return new ResponseResult(data,total);
    }

    public static ResponseResult error(String code,String message){

        return new ResponseResult(code,message);
    }
    public static ResponseResult error(String code,Object data,String message){

        return new ResponseResult(code,data,message);
    }

    public ResponseResult(String code,String message) {
        this.code= code;
        this.success=false;
        this.message = message;
    }
    public ResponseResult(String code,Object data,String message) {
        this.code= code;
        this.success=false;
        this.data=data;
        this.message = message;
    }

}
