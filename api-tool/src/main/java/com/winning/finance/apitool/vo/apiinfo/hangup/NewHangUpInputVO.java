package com.winning.finance.apitool.vo.apiinfo.hangup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>api-tool</p>
 * 新建挂起api信息
 * @author cq
 * @Description
 * @date 2020/7/29 10:11
 */
@Data
@ApiModel(value = "NewHangUpInputVO", description = "新建挂起api信息入参")
public class NewHangUpInputVO {

    @NotNull(message = "分组标识分组标识不为空")
    @ApiModelProperty(value = "分组标识",required = true,position = 10)
    private Long groupId;

    @NotBlank(message = "API名称不为空")
    @ApiModelProperty(value = "API名称",required = true,position = 20)
    private String apiName;

    @NotBlank(message = "API的URL不为空")
    @ApiModelProperty(value = "API的URL",required = true,position = 30)
    private String apiUrl;


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

    @ApiModelProperty(value = "说明",position = 100)
    private String memo;

    @ApiModelProperty(value = "API的入参列表",position = 110)
    private List<ParameterVO> inputParameterList;

    @ApiModelProperty(value = "API的出参列表",position = 120)
    private List<ParameterVO> OutputParameterList;
}
