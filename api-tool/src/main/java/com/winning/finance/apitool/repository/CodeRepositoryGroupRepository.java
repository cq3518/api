package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.CodeRepositoryGroupPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/28 13:34
 */
@Repository
public interface CodeRepositoryGroupRepository extends JpaRepository<CodeRepositoryGroupPO,Long> {

    /**
     * 根据 代码仓库 【分组名称】分组名称【父级分组标识】
     * @param codeRepositoryId 代码仓库
     * @param groupName 分组名称
     * @param parentGroupId 父分组id
     * @param isDel 删除标识
     * @return 数量
     */
    @Query("select count(1) from CodeRepositoryGroupPO po " +
            "where po.codeRepositoryId=?1 and " +
            "po.groupName=?2 and po.parentGroupId=?3 " +
            "and po.isDel=?4")
    Integer getByParentIdAndName(Long codeRepositoryId,
                                 String groupName,
                                 Long parentGroupId,
                                 Integer isDel);

    /**
     * 逻辑删除
     * @param groupId 分组id
     * @param isDelNo 删除标识
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update CodeRepositoryGroupPO set isDel=?2 where groupId=?1")
    void deleteByGroupId(Long groupId, Integer isDelNo);
    /**
     * 更新数据
     * @param groupId 分组id
     * @param groupName 分组名称
     * @param parentGroupId 分组父id
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update CodeRepositoryGroupPO set groupName=?2," +
            " parentGroupId=?3  where groupId=?1")
    void updatePO(Long groupId, String groupName, Long parentGroupId);

    /**
     * 通过代码仓库标识获取分组信息
     * @param codeRepositoryId 代码仓库标识
     * @param isDel 删除标识
     * @return 分组信息
     */
    @Query("select  po from CodeRepositoryGroupPO po where po.codeRepositoryId=?1 and po.isDel=?2 ")
    List<CodeRepositoryGroupPO> listByCodeRepositoryId(Long codeRepositoryId,Integer isDel);

    /**
     * 通过分组标识查询当前所属仓库
     * @param groupId 分组标识
     * @param isDelYes 删除标识
     * @return 所属仓库
     */
    @Query("select  po.codeRepositoryId from CodeRepositoryGroupPO po where po.groupId=?1 and po.isDel=?2")
    Long getCodeRepositoryIdById(Long groupId, Integer isDelYes);
}
