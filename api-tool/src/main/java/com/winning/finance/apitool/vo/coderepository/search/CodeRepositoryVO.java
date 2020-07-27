package com.winning.finance.apitool.vo.coderepository.search;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 16:06
 */
@Data
public class CodeRepositoryVO {


    /**
     * 代码仓库标识
     */
    private Long codeRepositoryId;


    /**
     * 仓库架构类别代码
     */
    private Long repositoryArchitectTypeCode;

    /**
     * 仓库业务类别代码
     */
    private Long repositoryBusinessTypeCode;

    /**
     * 仓库名称
     */
    private String repositoryName;
    /**
     * 服务APPID
     */
    private BigInteger appId;

    /**
     * 异常类别编码
     */
    private BigInteger exceptionClassNo;

    /**
     * 默认端口号
     */
    private BigInteger defaultPort;

    /**
     * APP名称
     */
    private String appName;

    /**
     * 接口基本路径
     */
    private String interfaceBasePath;

    /**
     * 仓库状态代码
     */
    private Long repositoryStatusCode;

    /**
     * 创建人
     */
    private String createBy;


    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 最后更新时间
     */
    private Date modifiedAt;

    /**
     * 说明
     */
    private String explain;

    /**
     * 当前API编号
     */
    private String currApiNo;

}
