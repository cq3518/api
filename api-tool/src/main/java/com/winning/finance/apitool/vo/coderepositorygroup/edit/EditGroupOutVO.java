package com.winning.finance.apitool.vo.coderepositorygroup.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>api-tool</p>
 * 修改分组信息
 * @author cq
 * @Description
 * @date 2020/7/28 13:41
 */
@Data
@ApiModel(value = "EditGroupOutVO",description = "修改分组信息出参")
public class EditGroupOutVO {

    @ApiModelProperty(value = "分组标识",position = 10)
    private Long groupId;
}
