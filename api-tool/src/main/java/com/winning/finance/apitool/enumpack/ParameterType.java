package com.winning.finance.apitool.enumpack;

import com.winning.finance.apitool.base.BusinessException;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>api-tool</p>
 *参数类型代码
 * @author cq
 * @Description
 * @date 2020/7/29 13:17
 */
@Getter
public enum ParameterType {


    INPUT_PARAMETER(28L,  "入参"),
    OUT_PARAMETER(29L,  "出参");


    private Long code;
    private String name;

    ParameterType(Long code, String name) {
        this.code = code;
        this.name = name;
    }
    public static ParameterType getInstance(Long code){
        for(ParameterType temp : ParameterType.values()){
            if(Objects.equals(code,temp.getCode())){
                return temp;
            }
        }
        throw new BusinessException(":找不到对应的参数类型代码" );
    }
}
