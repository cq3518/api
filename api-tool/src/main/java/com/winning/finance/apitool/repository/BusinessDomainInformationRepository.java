package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.BusinessDomainInformationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 15:03
 */
@Repository
public interface BusinessDomainInformationRepository extends JpaRepository<BusinessDomainInformationPO,Long> {
}
