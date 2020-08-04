package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.ApiParameterInformationPO;
import com.winning.finance.apitool.entity.ApiParameterInformationUpdatePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 10:51
 */
@Repository
public interface ApiParameterInformationRepository extends JpaRepository<ApiParameterInformationPO,Long> {


    /**
     * API的参数信息
     * @param apiId apiId
     * @return API的参数信息
     */
    @Query("select  po from ApiParameterInformationPO po where po.apiId = ?1")
    List<ApiParameterInformationPO> listByApiId(Long apiId);
    /**
     * API的参数信息
     * @param apiIds apiIds
     * @return API的参数信息
     */
    @Query("select  po from ApiParameterInformationPO po where po.apiId in (?1) ")
    List<ApiParameterInformationPO> listByApiIds(List<Long> apiIds);
}
