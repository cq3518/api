package com.winning.finance.apitool.vo.apiinfo.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * <p>api-tool</p>
 * 根据API标识查询API信息
 * @author cq
 * @Description
 * @date 2020/7/30 14:21
 */
@Data
@ApiModel(value = "ApiInfoByIdOutVO",description = "根据API标识查询API信息出参")
public class ApiInfoByIdOutVO {

    @NotNull(message = "API标识不为空")
    @ApiModelProperty(value = "API标识",required = true,position = 10)
    private Long apiId;

    @NotBlank(message = "API名称不为空")
    @ApiModelProperty(value = "API名称",required = true,position = 20)
    private String apiName;

    @NotBlank(message = "API的URL不为空")
    @ApiModelProperty(value = "API的URL",required = true,position = 30)
    private String apiUrl;

    @NotBlank(message = "API编号不为空")
    @ApiModelProperty(value = "API编号",required = true,position = 30)
    private String apiNo;

    @NotNull(message = "API状态代码不为空")
    @ApiModelProperty(value = "API状态代码",required = true,position = 40)
    private Long apiStatusCode;

    @NotNull(message = "请求方式代码不为空")
    @ApiModelProperty(value = "请求方式代码",required = true,position = 50)
    private Long requestMethodCode;

    @NotBlank(message = "API描述不为空")
    @ApiModelProperty(value = "API描述",required = true,position = 60)
    private String apiDescription;

    @NotBlank(message = "业务规则不为空")
    @ApiModelProperty(value = "业务规则",required = true,position = 70)
    private String businessRule;

    @NotNull(message = "是否分页代码不为空")
    @ApiModelProperty(value = "是否分页代码",required = true,position = 80)
    private Long allowPageCode;


    @ApiModelProperty(value = "创建人",position = 90)
    private String createBy;

    @ApiModelProperty(value = "创建时间",required = true,position = 91)
    private Date createAt;

    @ApiModelProperty(value = "最后更新时间",required = true,position = 92)
    private String modifiedAt;

    @ApiModelProperty(value = "说明",position = 100)
    private String memo;

    @ApiModelProperty(value = "签出人",position = 101)

    private String checkOutBy;
    @ApiModelProperty(value = "签出时间",position = 102)
    private Date checkOutAt;

    @ApiModelProperty(value = "API的入参列表",position = 110)
    private List<ApiParameter> inputParameterList;

    @ApiModelProperty(value = "API的出参列表",position = 120)
    private List<ApiParameter> OutputParameterList;
}
