package com.winning.finance.apitool.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 10:55
 */
@Entity
@Data
@Table(name="CODE_REPOSITORY_INFORMATION")
public class CodeRepositoryInformationPO {


    /**
     * 代码仓库标识
     */
    @Id
    @Column(name = "CODE_REPOSITORY_ID")
    private Long codeRepositoryId;

    /**
     * 业务域标识
     */
    @Basic
    @Column(name = "BUSINESS_DOMAIN_ID")
    private Long businessDomainId;

    /**
     * 仓库架构类别代码
     */
    @Basic
    @Column(name = "REPOSITORY_ARCHITECT_TYPE_CODE")
    private Long repositoryArchitectTypeCode;

    /**
     * 仓库业务类别代码
     */
    @Basic
    @Column(name = "REPOSITORY_BUSINESS_TYPE_CODE")
    private Long repositoryBusinessTypeCode;

    /**
     * 仓库名称
     */
    @Basic
    @Column(name = "REPOSITORY_NAME")
    private String repositoryName;

    /**
     * APP名称
     */
    @Basic
    @Column(name = "APP_NAME")
    private String appName;

    /**
     * 服务APPID
     */
    @Basic
    @Column(name = "APP_ID")
    private BigInteger appId;

    /**
     * 异常类别编码
     */
    @Basic
    @Column(name = "exception_class_no")
    private BigInteger exceptionClassNo;

    /**
     * 默认端口号
     */
    @Basic
    @Column(name = "DEFAULT_PORT")
    private BigInteger defaultPort;

    /**
     * 接口基本路径
     */
    @Basic
    @Column(name = "INTERFACE_BASE_PATH")
    private String interfaceBasePath;

    /**
     * 仓库状态代码
     */
    @Basic
    @Column(name = "REPOSITORY_STATUS_CODE")
    private Long repositoryStatusCode;

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
    @Column(name = "EXPLAIN")
    private String explain;

    /**
     * 当前API编号
     */
    @Basic
    @Column(name = "CURR_API_NO")
    private String currApiNo;
}
