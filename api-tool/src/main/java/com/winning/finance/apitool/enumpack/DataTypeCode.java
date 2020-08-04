package com.winning.finance.apitool.enumpack;

import com.winning.finance.apitool.base.BusinessException;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/8/4 13:54
 */
@Getter
public enum DataTypeCode {

    DATA_LONG(30L,  "Long"),
    DATA_STRING(31L,  "String"),
    DATA_DATE(32L,  "Date"),
    DATA_LIST(33L,  "List"),
    DATA_ARRAY_LONG(34L,  "Long[]"),
    DATA_LIST_LONG(35L,  "List<Long>"),
    DATA_BIG_DECIMAL(36L,  "BigDecimal"),
    DATA_INTEGER(37L,  "Integer"),
    DATA_OBJECT(38L,  "Object"),
    DATA_BOOLEAN(39L,  "Boolean");


    private Long code;
    private String name;

    DataTypeCode(Long code, String name) {
        this.code = code;
        this.name = name;
    }
    public static DataTypeCode getInstance(Long code){
        for(DataTypeCode temp : DataTypeCode.values()){
            if(Objects.equals(code,temp.getCode())){
                return temp;
            }
        }
        throw new BusinessException(":找不到对应的数据类型代码" );
    }

}
