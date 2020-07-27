package com.winning.finance.apitool.vo.coderepository.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>api-tool</p>
 * 查询业务域列表
 * @author cq
 * @Description
 * @date 2020/7/27 14:53
 */
@Data
@ApiModel(value = "QueryAllOutVO", description = "查询业务域列表出参")
public class QueryAllOutVO {

    @ApiModelProperty(value = "业务域列表",required = true,position = 10)
    private List<DoMainInfoVO> data;

}
