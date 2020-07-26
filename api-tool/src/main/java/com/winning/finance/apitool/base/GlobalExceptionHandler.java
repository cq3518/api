package com.winning.finance.apitool.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/25 15:22
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseResult businessException(HttpServletRequest request, BusinessException e){
        log.error("********************Throw BusinessException.url:{} ERROR:{}********************",request.getRequestURL(), e.getMessage(), e);
        String message=ResultCode.FALSE.getMsg() + "。异常原因：" + e.getMessage();
        String code=ResultCode.FALSE.getCode();
        return ResponseResult.error(code,message);
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandle(HttpServletRequest request,Exception e){
        log.error("********************Throw Exception.url:{} ERROR:{}********************",request.getRequestURL(), e.getMessage(), e);
        String message=ResultCode.FALSE.getMsg() + "。异常原因：" + e.getMessage();
        String code=ResultCode.FALSE.getCode();
        return ResponseResult.error(code,message);

    }

    /**
     * 400错误
     * @param e
     * @return java.lang.ResponseResult
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, TypeMismatchException.class, MissingServletRequestParameterException.class})
    public ResponseResult requestNotReadable(HttpServletRequest request, HttpMessageNotReadableException e) {
        return resultFormat(request, ResultCode.NOT_FOUND, e);
    }

    /**
     * 404错误
     * @param e
     * @return java.lang.ResponseResult
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseResult requestNotReadable(HttpServletRequest request, NoHandlerFoundException e) {
        return resultFormat(request, ResultCode.NOT_FOUND, e);
    }

    /**
     * 405错误
     * @param e
     * @return java.lang.ResponseResult
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseResult request405(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        return resultFormat(request, ResultCode.NOT_FOUND_405, e);
    }

    /**
     * 406错误
     * @param e
     * @return java.lang.ResponseResult
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseResult request406(HttpServletRequest request, HttpMediaTypeNotAcceptableException e) {
        return resultFormat(request, ResultCode.NOT_FOUND_406, e);
    }

    /**
     * Throwable拦截器
     * @param request
     * @param e
     * @return com.openailab.oascloud.common.model.ResponseResult
     * @date 2019/6/27
     */
    @ExceptionHandler(Throwable.class)
    public ResponseResult exceptionHandle(HttpServletRequest request, Throwable e) {
        return resultFormat(request, ResultCode.INTERNAL_SERVER_ERROR, e);
    }

    /**
     * 格式封装
     *
     * @param request
     * @param resultCode
     * @param e
     * @return com.openailab.oascloud.common.model.ResponseResult
     */
    private ResponseResult resultFormat(HttpServletRequest request, ResultCode resultCode, Throwable e) {
        log.error("********************Throw Exception.url:{} ERROR:{}********************", request.getRequestURL(), e.getMessage(), e);
        String message=resultCode.getMsg() + "。异常原因：" + e.getMessage();
        String code=resultCode.getCode();
        return ResponseResult.error(code,message);
    }

    /**
     * 参数校验统一异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return ResponseResult.error(ResultCode.BODY_NOT_MATCH.getCode(),fieldError,ResultCode.BODY_NOT_MATCH.getMsg());
    }
}
