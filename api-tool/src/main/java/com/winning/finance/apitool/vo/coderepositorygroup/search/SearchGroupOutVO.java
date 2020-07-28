package com.winning.finance.apitool.vo.coderepositorygroup.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>api-tool</p>
 * 查询分组信息
 * @author cq
 * @Description
 * @date 2020/7/28 15:41
 */
@Data
@ApiModel(value = "SearchGroupOutVO",description = "查询分组信息出参")
public class SearchGroupOutVO {


    @ApiModelProperty(value = "分组列表",position = 10)
    private List<GroupInfo> data;


}
