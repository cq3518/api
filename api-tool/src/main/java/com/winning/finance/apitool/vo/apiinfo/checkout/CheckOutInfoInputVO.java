package com.winning.finance.apitool.vo.apiinfo.checkout;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 *签出API信息以进行修改
 * @author cq
 * @Description
 * @date 2020/7/30 13:05
 */
@Data
@ApiModel(value = "CheckOutInfoInputVO", description = "签出API信息以进行修改入参")
public class CheckOutInfoInputVO {

    @NotNull(message = "API标识不为空")
    @ApiModelProperty(value = "API标识",required = true,position = 10)
    private Long apiId;

    @NotBlank(message = "签出人不为空")
    @ApiModelProperty(value = "签出人",required = true,position = 20)
    private String checkOutBy;

}
