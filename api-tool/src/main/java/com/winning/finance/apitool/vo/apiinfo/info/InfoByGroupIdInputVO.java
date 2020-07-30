package com.winning.finance.apitool.vo.apiinfo.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>api-tool</p>
 * 根据分组标识列表查询API信息列表
 *
 * @author cq
 * @Description
 * @date 2020/7/30 9:20
 */
@Data
@ApiModel(value = "InfoByGroupIdInputVO", description = "根据分组标识列表查询API信息列表入参")
public class InfoByGroupIdInputVO {

    @NotEmpty(message = "分组标识列表不为空")
    @ApiModelProperty(value = "分组标识列表", required = true, position = 10)
    private List<Long> groupIds;

}
