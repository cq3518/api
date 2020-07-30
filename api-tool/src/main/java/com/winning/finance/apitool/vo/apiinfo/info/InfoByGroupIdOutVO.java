package com.winning.finance.apitool.vo.apiinfo.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>api-tool</p>
 *根据分组标识列表查询API信息列表
 * @author cq
 * @Description
 * @date 2020/7/30 9:20
 */
@Data
@ApiModel(value = "InfoByGroupIdOutVO",description = "根据分组标识列表查询API信息列表出参")
public class InfoByGroupIdOutVO {

    @ApiModelProperty(value = "分组标识", required = true, position = 10)
    private Long groupId;

    @ApiModelProperty(value = "API标识", required = true, position = 20)
    private Long apiId;

    @ApiModelProperty(value = "API编号", required = true, position = 30)
    private String apiNo;

    @ApiModelProperty(value = "API名称", required = true, position = 40)
    private String apiName;

    @ApiModelProperty(value = "API的URL", required = true, position = 50)
    private String apiUrl;

    @ApiModelProperty(value = "API状态代码", required = true, position = 60)
    private Long apiStatusCode;

    @ApiModelProperty(value = "请求方式代码", required = true, position = 70)
    private Long requestMethodCode;

    @ApiModelProperty(value = "API描述", required = true, position = 80)
    private String apiDescription;

    @ApiModelProperty(value = "业务规则", required = true, position = 90)
    private String businessRule;

    @ApiModelProperty(value = "是否分页代码", required = true, position = 100)
    private Long AllowPageCode;

    @ApiModelProperty(value = "创建人", required = true, position = 110)
    private String createBy;

    @ApiModelProperty(value = "创建时间", required = true, position = 120)
    private Date createAt;

    @ApiModelProperty(value = "最后更新时间", required = true, position = 130)
    private Date modifiedAt;

    @ApiModelProperty(value = "说明", position = 140)
    private String memo;

    @ApiModelProperty(value = "挂起状态代码", position = 150)
    private Long hangUpStatusCode;

    @ApiModelProperty(value = "签出人", position = 160)
    private String checkOutBy;

    @ApiModelProperty(value = "签出时间",  position = 170)
    private Date checkOutAt;

    @ApiModelProperty(value = "API修改标识",  position = 180)
    private Long apiUpdateId;
}
