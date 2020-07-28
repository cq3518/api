package com.winning.finance.apitool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 16:36
 */
@Entity
@Data
@Table(name="SYS_DICT_TYPE")
public class SysDictTypePO {
    /**
     * 字典主键
     */
    @Id
    @Column(name = "DICT_ID")
    private Long dictId;

    /**
     * 字典名称
     */
    @Basic
    @Column(name = "DICT_NAME")
    private String dictName;

    /**
     * 状态（0正常 1停用）
     */
    @Basic
    @Column(name = "STATUS")
    private Integer status;

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
    @Column(name = "memo")
    private String memo;

}
