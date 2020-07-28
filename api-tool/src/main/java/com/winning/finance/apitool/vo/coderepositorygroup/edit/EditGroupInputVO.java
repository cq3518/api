package com.winning.finance.apitool.vo.coderepositorygroup.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 *  修改分组信息
 * @author cq
 * @Description
 * @date 2020/7/28 13:41
 */
@Data
@ApiModel(value = "EditGroupInputVO",description = "修改分组信息入参")
public class EditGroupInputVO {


    @NotNull(message = "分组标识")
    @ApiModelProperty(value = "分组标识",position = 10)
    private Long groupId;

    @NotBlank(message = "分组名称不为空")
    @ApiModelProperty(value = "分组名称",position = 20)
    private String groupName;

    @NotNull(message = "分组标识")
    @ApiModelProperty(value = "父级分组标识",position = 30)
    private Long parentGroupId;
}
