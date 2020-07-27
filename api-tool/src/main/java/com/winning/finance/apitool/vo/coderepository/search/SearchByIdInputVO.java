package com.winning.finance.apitool.vo.coderepository.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 * 根据业务域编码查询代码仓库信息
 * @author cq
 * @Description
 * @date 2020/7/27 16:02
 */
@Data
@ApiModel(value = "SearchByIdInputVO", description = "根据业务域编码查询代码仓库信息入参")
public class SearchByIdInputVO {


    @ApiModelProperty(value = "业务域标识",required = true,position = 10)
    @NotNull(message = "业务域标识不能空")
    private Long businessDomainId;
}
