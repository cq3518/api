package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.BusinessDomainInformationPO;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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


    @Query("select po " +
            "from  BusinessDomainInformationPO po where po.businessDomainId =?1")
    BusinessDomainInformationPO getByDomainId(Long businessDomainId);
}
