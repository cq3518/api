package com.winning.finance.apitool.base;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/25 15:38
 */
public enum  ResultCode {

    SUCCESS("200", "请求成功!"),
    FALSE("-1", "请求失败!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "服务器找不到请求!"),
    NOT_FOUND_405("405", "不允许此方法!"),
    NOT_FOUND_406("406", " 发送标题不可接受!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
    ;
    /** 错误码 */
    private String code;

    /** 错误描述 */
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
