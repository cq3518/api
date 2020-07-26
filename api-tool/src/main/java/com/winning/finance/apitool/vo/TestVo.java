package com.winning.finance.apitool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/25 17:40
 */
@Data
@ApiModel(value = "TestVo",description = "测试vo")
public class TestVo {

    @ApiModelProperty(value = "主键id",required = true,position = 10)
    @NotNull(message = "id不能空")
    private Integer id;

    @ApiModelProperty(value = "姓名",required = true,position = 20)
    @NotBlank(message = "姓名不能为空")
    private String name;
}
