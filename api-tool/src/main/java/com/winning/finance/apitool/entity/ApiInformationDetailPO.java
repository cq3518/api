package com.winning.finance.apitool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>api-tool</p>
 * API详细信息
 * @author cq
 * @Description
 * @date 2020/7/29 9:51
 */
@Entity
@Data
@Table(name="API_INFORMATION_DETAIL")
public class ApiInformationDetailPO {
    /**
     * API标识
     */
    @Id
    @Column(name = "API_ID")
    private Long apiId;

    /**
     * 分组标识
     */
    @Basic
    @Column(name = "GROUP_ID")
    private Long groupId;


    /**
     * API编号
     */
    @Basic
    @Column(name = "API_NO")
    private String apiNo;

    /**
     * APP名称
     */
    @Basic
    @Column(name = "API_NAME")
    private String apiName;

    /**
     * API的URL
     */
    @Basic
    @Column(name = "API_URL")
    private String apiUrl;

    /**
     * API状态代码
     */
    @Basic
    @Column(name = "API_STATUS_CODE")
    private Long apiStatusCode;

    /**
     * 请求方式代码
     */
    @Basic
    @Column(name = "REQUEST_METHOD_CODE")
    private Long requestMethodCode;

    /**
     * API描述
     */
    @Basic
    @Column(name = "API_DESCRIPTION")
    private String apiDescription;

    /**
     * 业务规则
     */
    @Basic
    @Column(name = "BUSINESS_RULE")
    private String businessRule;

    /**
     * 是否分页代码
     */
    @Basic
    @Column(name = "ALLOW_PAGE_CODE")
    private Long allowPageCode;

    /**
     * 创建人
     */
    @Basic
    @Column(name = "CREATE_BY")
    private String createBy;

    /**
     * 创建时间
     */
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;

    /**
     * 最后更新时间
     */
    @Basic
    @Column(name = "MODIFIED_AT")
    private Date modifiedAt;

    /**
     * 说明
     */
    @Basic
    @Column(name = "MEMO")
    private String memo;

    /**
     * 逻辑删除标志
     */
    @Basic
    @Column(name = "IS_DEL")
    private Integer isDel;

    /**
     * 签出人
     */
    @Basic
    @Column(name = "CHECK_OUT_BY")
    private String checkOutBy;

    /**
     * 签出时间
     */
    @Basic
    @Column(name = "CHECK_OUT_AT")
    private Date checkOutAt;

}
