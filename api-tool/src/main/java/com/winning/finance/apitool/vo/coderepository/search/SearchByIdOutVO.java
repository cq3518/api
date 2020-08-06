package com.winning.finance.apitool.vo.coderepository.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>api-tool</p>
 *根据业务域编码查询代码仓库信息
 * @author cq
 * @Description
 * @date 2020/7/27 16:02
 */
@Data
@ApiModel(value = "SearchByIdOutVO", description = "根据业务域编码查询代码仓库信息出参")
public class SearchByIdOutVO {

    @ApiModelProperty(value = "代码仓库列表",required = true,position = 10)
    private List<CodeRepositoryVO> data;
}
