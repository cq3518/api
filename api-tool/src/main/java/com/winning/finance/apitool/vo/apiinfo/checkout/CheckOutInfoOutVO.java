package com.winning.finance.apitool.vo.apiinfo.checkout;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>api-tool</p>
 *签出API信息以进行修改
 * @author cq
 * @Description
 * @date 2020/7/30 13:05
 */
@Data
@ApiModel(value = "CheckOutInfoOutVO", description = "签出API信息以进行修改出参")
public class CheckOutInfoOutVO {

    @ApiModelProperty(value = "API修改标识",required = true,position = 10)
    private Long apiUpdateId;
}
