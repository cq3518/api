package com.winning.finance.apitool.repository;

import cn.hutool.core.date.DateTime;
import com.winning.finance.apitool.entity.ApiInformationDetailPO;
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
 * @date 2020/7/29 10:05
 */
@Repository
public interface ApiInformationDetailRepository extends JpaRepository<ApiInformationDetailPO,Long> {


    /**
     * 返回api信息
     * @param groupIds 分组标识
     * @param isDelYes 删除标识
     * @return api信息
     */
    @Query("select po from ApiInformationDetailPO po where  po.groupId in (?1) and po.isDel = ?2")
    List<ApiInformationDetailPO> listByGroupId(List<Long> groupIds, Integer isDelYes);

    /**
     * 有效的api信息
     * @param apiId apiId
     * @param code api代码
     * @param isDelYes 删除标识
     * @return api信息
     */
    @Query("select po from ApiInformationDetailPO po where  po.apiId = ?1 " +
            "and po.apiStatusCode <> ?2 and po.isDel=?3")
    ApiInformationDetailPO getByIdAndApiState(Long apiId, Long code, Integer isDelYes);

    /**
     * 更新api信息表
     * @param apiId apiId
     * @param checkOutBy 签出人
     * @param now 签出时间
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(" update  ApiInformationDetailPO  set checkOutBy=?2,checkOutAt=?3  where apiId=?1")
    void updateById(Long apiId, String checkOutBy, DateTime now);

    /**
     * 查询api信息
     * @param apiId apiId
     * @param isDelYes 删除标识
     * @return api信息
     */
    @Query("select po from ApiInformationDetailPO po where  po.apiId = ?1 " +
            " and po.isDel=?2")
    ApiInformationDetailPO getById(Long apiId, Integer isDelYes);


    @Query("select count(1) from ApiInformationDetailPO po " +
            "where po.apiName=?1 or " +
            "po.apiUrl=?2 ")
    Integer countByApiInfo(String apiName, String apiUrl);
}
