package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.ApiInformationDetailPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 10:05
 */
@Repository
public interface ApiInformationDetailRepository extends JpaRepository<ApiInformationDetailPO,Long> {


}
