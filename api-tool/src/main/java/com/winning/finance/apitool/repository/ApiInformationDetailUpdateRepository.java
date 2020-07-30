package com.winning.finance.apitool.repository;

import cn.hutool.core.date.DateTime;
import com.winning.finance.apitool.entity.ApiInformationDetailUpdatePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 10:05
 */
@Repository
public interface ApiInformationDetailUpdateRepository extends JpaRepository<ApiInformationDetailUpdatePO,Long> {

    /**
     * 获取挂起的api
     * @param apiUpdateIds  API修改标识列表
     * @param hangUpStatusCodes 挂起状态
     * @param isDelYes 删除标识
     * @return 获取挂起的api
     */
    @Query("select po from ApiInformationDetailUpdatePO po where po.apiUpdateId in (?1) " +
            "and po.hangUpStatusCode in (?2) and po.isDel=?3")
    List<ApiInformationDetailUpdatePO> listByApiUpdateIds(List<Long> apiUpdateIds,
                                                          List<Long> hangUpStatusCodes,
                                                          Integer isDelYes);

    /**
     * 更新挂起表
     * @param apiUpdateIds API修改标识列表
     * @param code  挂起状态
     * @param now 修改时间
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(" update  ApiInformationDetailUpdatePO  set hangUpStatusCode=?2,modifiedAt=?3  where apiUpdateId=?1")
    void updateByApiUpdateIds(List<Long> apiUpdateIds, Long code, DateTime now);

    /**
     *  更新
     * @param apiUpdateId 挂起id
     * @param code 状态
     * @param now 时间
     * @param changeId 变更id
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(" update  ApiInformationDetailUpdatePO  set hangUpStatusCode=?2,modifiedAt=?3 ,apiChangeId=?4 where apiUpdateId=?1")
    void updateStatusByApiUpdateId(Long apiUpdateId, Long code, Date now, long changeId);

    /**
     *  获取api挂起信息
     * @param groupIds 分组id
     * @param code  挂起new 状态
     * @param isDelYes 删除标识
     * @return api 挂起信息
     */
    @Query("select po from ApiInformationDetailUpdatePO po where  po.groupId in (?1) and po.hangUpStatusCode =?2 " +
            "and po.isDel=?3")
    List<ApiInformationDetailUpdatePO> listByGroupId(List<Long> groupIds, Long code, Integer isDelYes);

    /**
     * 挂起的api信息
     * @param apiId apiId
     * @param code 挂起状态
     * @param isDelYes 删除标识
     * @return 挂起的api信息
     */
    @Query("select po from ApiInformationDetailUpdatePO po where  po.apiId = ?1 and po.hangUpStatusCode =?2 " +
            "and po.isDel=?3")
    ApiInformationDetailUpdatePO getByIdAndHangUpStatus(Long apiId, Long code, Integer isDelYes);

    /**
     * 挂起的api信息
     * @param apiIds apiId
     * @param code 挂起状态
     * @param isDelYes 删除标识
     * @return 挂起的api信息
     */
    @Query("select po from ApiInformationDetailUpdatePO po where  po.apiId in (?1) and po.hangUpStatusCode =?2 " +
            "and po.isDel=?3")
    List<ApiInformationDetailUpdatePO> ListByIdAndHangUpStatus(List<Long> apiIds, Long code, Integer isDelYes);

    @Query("select po from ApiInformationDetailUpdatePO po where  po.apiUpdateId = ?1 and po.isDel =?2 ")
    ApiInformationDetailUpdatePO getById(Long apiUpdateId, Integer isDelYes);

    @Query("select po from ApiInformationDetailUpdatePO po where  po.groupId in (?1)  " +
            "and po.isDel=?2")
    List<ApiInformationDetailUpdatePO> listByGroupId(List<Long> groupId, Integer isDelYes);

}
