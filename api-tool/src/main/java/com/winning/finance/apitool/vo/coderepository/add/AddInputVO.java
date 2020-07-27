package com.winning.finance.apitool.vo.coderepository.add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * <p>api-tool</p>
 * 代码仓库新增入参 dto
 * @author cq
 * @Description
 * @date 2020/7/27 12:55
 */
@Data
@ApiModel(value = "AddInputDTO", description = "代码仓库新增入参")
public class AddInputVO {

    @ApiModelProperty(value = "仓库架构类别代码",required = true,position = 10)
    @NotNull(message = "仓库架构类别代码不能空")
    private Long repositoryArchitectTypeCode;

    @ApiModelProperty(value = "仓库业务类别代码",position = 20)
    private Long repositoryBusinessTypeCode;

    @ApiModelProperty(value = "仓库名称",required = true,position = 30)
    @NotBlank(message = "仓库名称不能空")
    private String repositoryName;

    @ApiModelProperty(value = "服务appId",required = true,position = 40)
    @NotNull(message = "服务appId不能空")
    private BigInteger appId;

    @ApiModelProperty(value = "异常类别编码",required = true,position = 50)
    @NotNull(message = "异常类别编码不能空")
    private BigInteger exceptionClassNo;

    @ApiModelProperty(value = "默认端口号",required = true,position = 60)
    @NotNull(message = "默认端口号不能空")
    private BigInteger defaultPort;

    @ApiModelProperty(value = "APP名称",required = true,position = 70)
    @NotNull(message = "APP名称不能空")
    private String appName;

    @ApiModelProperty(value = "接口基本路径",position = 80)
    private String interfaceBasePath;

    @ApiModelProperty(value = "仓库状态代码",required = true,position = 90)
    @NotNull(message = "仓库状态代码不能空")
    private Long repositoryStatusCode;

    @ApiModelProperty(value = "创建人",required = true,position = 100)
    @NotBlank(message = "创建人不能空")
    private String createBy;

    @ApiModelProperty(value = "说明",position = 110)
    private String explain;

    @ApiModelProperty(value = "当前API编号",required = true,position = 120)
    @NotBlank(message = "当前API编号不能空")
    private String currApiNo;

    @ApiModelProperty(value = "业务域标识",required = true,position = 130)
    @NotNull(message = "业务域标识不能空")
    private Long businessDomainId;
}
