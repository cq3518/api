package com.winning.finance.apitool.vo.sysdictdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 16:53
 */
@Data
@ApiModel(value = "DictDateByIdOutVO", description = "根据字典主键查询字典数据信息列表出参")
public class DictDateByIdOutVO {

    @ApiModelProperty(value = "字典数据列表",position = 10)
    private List<DictData> data;
}
