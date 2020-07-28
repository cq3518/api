package com.winning.finance.apitool.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * <p>api-tool</p>
 * 仓库包含的分组信息
 * @author cq
 * @Description
 * @date 2020/7/28 13:29
 */
@Entity
@Data
@Table(name="CODE_REPOSITORY_GROUP")
public class CodeRepositoryGroupPO {


    /**
     * 分组标识
     */
    @Id
    @Basic
    @Column(name = "GROUP_ID")
    private Long groupId;

    /**
     * 代码仓库标识
     */
    @Basic
    @Column(name = "CODE_REPOSITORY_ID")
    private Long codeRepositoryId;

    /**
     * 分组名称
     */
    @Basic
    @Column(name = "GROUP_NAME")
    private String groupName;

    /**
     * 父级分组标识
     */
    @Basic
    @Column(name = "PARENT_GROUP_ID")
    private Long parentGroupId;

    /**
     * 删除标识
     */
    @Basic
    @Column(name = "IS_DEL")
    private Integer isDel;
}
