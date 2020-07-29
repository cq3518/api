package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.ApiParameterInformationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 10:51
 */
@Repository
public interface ApiParameterInformationRepository extends JpaRepository<ApiParameterInformationPO,Long> {


}
