package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.ApiParameterInformationUpdatePO;
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
 * @date 2020/7/29 10:51
 */
@Repository
public interface ApiParameterInformationUpdateRepository extends JpaRepository<ApiParameterInformationUpdatePO,Long> {

    /**
     *API修改标识
     * @param apiUpdateId
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(" delete  from ApiParameterInformationUpdatePO where apiUpdateId=?1")
    void deleteByApiUpdateId(Long apiUpdateId);

    /**
     * API的参数信息（修改）
     * @param apiUpdateId 挂起的ids
     * @return API的参数信息（修改）
     */
    @Query("select  po from ApiParameterInformationUpdatePO po where po.apiUpdateId in (?1)")
    List<ApiParameterInformationUpdatePO> listByApiUpdateId(List<Long> apiUpdateId);

}
