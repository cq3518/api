package com.winning.finance.apitool.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 10:37
 */
@Entity
@Data
@Table(name="API_PARAMETER_INFORMATION")
public class ApiParameterInformationPO {



    /**
     *  API的参数标识
     */
    @Id
    @Column(name = "API_PARAMETER_ID")
    private Long apiParameterId;

    /**
     * API标识
     */
    @Basic
    @Column(name = "API_ID")
    private Long apiId;


    /**
     * 参数编码
     */
    @Basic
    @Column(name = "PARAMETER_NO")
    private String parameterNo;

    /**
     * 参数名称
     */
    @Basic
    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    /**
     * 参数类型代码
     */
    @Basic
    @Column(name = "PARAMETER_TYPE_CODE")
    private Long parameterTypeCode;

    /**
     * 数据类型代码
     */
    @Basic
    @Column(name = "DATA_TYPE_CODE")
    private Long dataTypeCode;

    /**
     * 是否必填代码
     */
    @Basic
    @Column(name = "REQUIRED_CODE")
    private Long requiredCode;

    /**
     * 默认值
     */
    @Basic
    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    /**
     * 说明
     */
    @Basic
    @Column(name = "MEMO")
    private String memo;

    /**
     * 父级参数标识
     */
    @Basic
    @Column(name = "PARENT_PARAMETER_ID")
    private Long parentParameterId;

}
