package com.winning.finance.apitool.vo.coderepositorygroup.delete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 *删除分组信息入参
 * @author cq
 * @Description
 * @date 2020/7/28 15:08
 */
@Data
@ApiModel(value = "DeleteGroupInputVO",description = "删除分组信息入参")
public class DeleteGroupInputVO {

    @NotNull(message = "分组标识")
    @ApiModelProperty(value = "分组标识",position = 10)
    private Long groupId;

}
