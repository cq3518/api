package com.winning.finance.apitool.vo.coderepository.getappid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>api-tool</p>
 *根据业务域标识、架构类别代码获取当前可用的appId
 * @author cq
 * @Description
 * @date 2020/8/7 10:27
 */
@Data
@ApiModel(value = "AppIdByDomainIdOutVO",description = "根据业务域标识、架构类别代码获取当前可用的appId出参")
public class AppIdByDomainIdOutVO {


    @ApiModelProperty(value = "服务APPID",position = 10)
    private Integer appId;

    @ApiModelProperty(value = "异常类别编码",position = 20)
    private Integer exceptionClassNo;

    @ApiModelProperty(value = "默认端口号",position = 30)
    private Integer defaultPort;

}
