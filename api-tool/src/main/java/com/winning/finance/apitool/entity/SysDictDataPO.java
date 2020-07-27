package com.winning.finance.apitool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 16:42
 */
@Entity
@Data
@Table(name="SYS_DICT_DATA")
public class SysDictDataPO {


    /**
     * 字典数据主键
     */
    @Id
    @Column(name = "DICT_DATA_ID")
    private Long dictDataId;

    /**
     * 字典主键
     */
    @Basic
    @Column(name = "DICT_ID")
    private Long dictId;

    /**
     * 字典数据说明
     */
    @Basic
    @Column(name = "DICT_DATA_DESC")
    private String dictDataDesc;

    /**
     * 字典排序
     */
    @Basic
    @Column(name = "DICT_DATA_SORT")
    private Integer dictDataSort;

    /**
     * 是否默认（1是 0否）
     */
    @Basic
    @Column(name = "IS_DEFAULT")
    private Integer isDefault;

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
}
