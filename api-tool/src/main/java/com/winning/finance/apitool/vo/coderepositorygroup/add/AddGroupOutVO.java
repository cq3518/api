package com.winning.finance.apitool.vo.coderepositorygroup.add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>api-tool</p>
 * 新增分组信息
 * @author cq
 * @Description
 * @date 2020/7/28 13:41
 */
@Data
@ApiModel(value = "AddGroupOutVO",description = "新增分组信息出参")
public class AddGroupOutVO {

    @ApiModelProperty(value = "分组标识",position = 10)
    private Long groupId;
}
