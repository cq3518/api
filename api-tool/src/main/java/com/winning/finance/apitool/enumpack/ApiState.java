package com.winning.finance.apitool.enumpack;

import com.winning.finance.apitool.base.BusinessException;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/30 13:18
 */
@Getter
public enum  ApiState {

    TO_BE_DEVELOPED(14L, "待开发"),
    IN_DEVELOPMENT(15L, "开发中"),
    COMPLETED(16L,  "已完成"),
    OBSOLETE(17L,  "已废弃");


    private Long code;
    private String name;

    ApiState(Long code, String name) {
        this.code = code;
        this.name = name;
    }
    public static ApiState getInstance(Long code){
        for(ApiState temp : ApiState.values()){
            if(Objects.equals(code,temp.getCode())){
                return temp;
            }
        }
        throw new BusinessException(":找不到对应的api状态代码" );
    }
}
