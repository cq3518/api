package com.winning.finance.apitool.vo.apiinfo.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 *根据签出API标识查询签出的API信息
 * @author cq
 * @Description
 * @date 2020/7/30 14:21
 */
@Data
@ApiModel(value = "ApiInfoByApiUpdateIdInputVO",description = "根据签出API标识查询签出的API信息入参")
public class ApiInfoByApiUpdateIdInputVO {

    @NotNull(message = "签出API标识不为空")
    @ApiModelProperty(value = "签出API标识",required = true,position = 10)
    private Long apiUpdateId;
}
