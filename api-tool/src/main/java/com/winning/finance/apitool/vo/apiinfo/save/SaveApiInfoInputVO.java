package com.winning.finance.apitool.vo.apiinfo.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 16:27
 */
@Data
@ApiModel(value = "SaveApiInfoInputVO",description = "")
public class SaveApiInfoInputVO {

    @NotEmpty(message = "API修改标识列表不为空")
    @ApiModelProperty(value = "API修改标识列表",required = true,position = 10)
    private List<Long> apiUpdateIds;

    @NotBlank(message = "提交人不为空")
    @ApiModelProperty(value = "提交人",required = true,position = 20)
    private String submitBy;

    @NotBlank(message = "API变更缘由不为空")
    @ApiModelProperty(value = "API变更缘由",required = true,position = 10)
    private String apiChangeReason;
}
