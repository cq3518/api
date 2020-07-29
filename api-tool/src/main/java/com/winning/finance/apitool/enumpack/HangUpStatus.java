package com.winning.finance.apitool.enumpack;

import com.winning.finance.apitool.base.BusinessException;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>api-tool</p>
 *挂起状态代码
 * @author cq
 * @Description
 * @date 2020/7/29 12:50
 */
@Getter
public enum HangUpStatus {

    NEW(24L, "新建中"),
    CHECKING_OUT(25L, "签出中"),
    CHECKING_IN(26L,  "已签入"),
    RESCINDED(27L,  "已撤销");


    private Long code;
    private String name;

    HangUpStatus(Long code, String name) {
        this.code = code;
        this.name = name;
    }
    public static HangUpStatus getInstance(Long code){
        for(HangUpStatus temp : HangUpStatus.values()){
            if(Objects.equals(code,temp.getCode())){
                return temp;
            }
        }
        throw new BusinessException(":找不到对应的挂起状态代码" );
    }
}
