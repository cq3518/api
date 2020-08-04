package com.winning.finance.apitool.enumpack;

import com.winning.finance.apitool.base.BusinessException;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/8/4 17:39
 */
@Getter
public enum RequestMethodCode {
    POST(18L,  "POST"),
    GET(19L,  "GET"),
    PUT(20L,  "PUT"),
    DELETE(21L,  "DELETE");


    private Long code;
    private String name;

    RequestMethodCode(Long code, String name) {
        this.code = code;
        this.name = name;
    }
    public static RequestMethodCode getInstance(Long code){
        for(RequestMethodCode temp : RequestMethodCode.values()){
            if(Objects.equals(code,temp.getCode())){
                return temp;
            }
        }
        throw new BusinessException(":找不到对应的请求方式代码" );
    }

}
