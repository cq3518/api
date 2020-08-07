package com.winning.finance.apitool.vo.coderepository.getappid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 *根据业务域标识、架构类别代码获取当前可用的appId
 * @author cq
 * @Description
 * @date 2020/8/7 10:27
 */
@Data
@ApiModel(value = "AppIdByDomainIdInputVO",description = "根据业务域标识、架构类别代码获取当前可用的appId入参")
public class AppIdByDomainIdInputVO {

    @NotNull(message = "业务域标识不为空")
    @ApiModelProperty(value = "业务域标识",position = 10)
    private Long businessDomainId;

    @NotNull(message = "仓库架构类别代码不为空")
    @ApiModelProperty(value = "仓库架构类别代码",position = 20)
    private Long repositoryArchitectTypeCode;
}
