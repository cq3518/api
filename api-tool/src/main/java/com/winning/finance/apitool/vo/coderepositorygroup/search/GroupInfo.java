package com.winning.finance.apitool.vo.coderepositorygroup.search;

import lombok.Data;

import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/28 15:47
 */
@Data
public class GroupInfo {

    /**
     * 分组id
     */
    private Long groupId;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 父分组id
     */
    private Long parentGroupId;

    /**
     * 子级分组列表
     */
    private List<GroupInfo> childGroupList;

}
