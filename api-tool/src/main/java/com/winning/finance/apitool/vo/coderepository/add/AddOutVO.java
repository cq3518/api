package com.winning.finance.apitool.vo.coderepository.add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 * 代码仓库新增出参
 * @author cq
 * @Description
 * @date 2020/7/27 13:06
 */
@Data
@ApiModel(value = "AddOutDTO", description = "代码仓库新增出参")
public class AddOutVO {

    @ApiModelProperty(value = "代码仓库标识",position = 10,required = true)
    @NotNull(message = "代码仓库标识")
    private Long codeRepositoryId;
}
