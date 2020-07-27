package com.winning.finance.apitool.vo.sysdictdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 * 根据字典主键查询字典数据信息列表
 * @author cq
 * @Description
 * @date 2020/7/27 16:53
 */
@Data
@ApiModel(value = "DictDateByIdInputVO", description = "根据字典主键查询字典数据信息列表入参")
public class DictDateByIdInputVO {

    @NotNull(message = "字典主键不能为空")
    @ApiModelProperty(value = "字典主键" ,required = true,position =10)
    private Long dictId;
}
