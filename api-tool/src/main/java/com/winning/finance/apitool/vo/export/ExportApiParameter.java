package com.winning.finance.apitool.vo.export;

import lombok.Data;

import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 10:53
 */
@Data
public class ExportApiParameter {

    /**
     * id
     *
     */
    private Long apiParameterId;
    /**
     * 参数编码
     */
    private String parameterNo;

    /**
     * 参数名称
     */
    private String parameterName;

    /**
     * 参数类型代码  标识 入参还是出参
     */
    private Long parameterTypeCode;

    /**
     * 数据类型代码
     */
    private Long dataTypeCode;
    /**
     * 数据类型代码-名称
     */
    private String dataTypeCodeName;

    /**
     * 是否必填代码
     */
    private Long requiredCode;
    private String requiredCodeName;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 说明
     */
    private String memo;

    /**
     * 父节点
     */
    private Long parentParameterId;

    /**
     * 参数列表
     */
    private List<ExportApiParameter> parameterVOS;


}
