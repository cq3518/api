package com.winning.finance.apitool.vo.apiinfo.delete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>api-tool</p>
 * 3.1.5 撤销挂起的API修改
 * @author cq
 * @Description
 * @date 2020/7/29 15:17
 */
@Data
@ApiModel(value = "DeleteHangUpInputVO", description = "撤销挂起的API修改入参")
public class DeleteHangUpInputVO {

    @NotEmpty
    @ApiModelProperty(value = "API修改标识列表",required = true,position = 10)
    private List<Long> apiUpdateIds;
}
