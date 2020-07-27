package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.CodeRepositoryInformationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 11:05
 */
@Repository
public interface CodeRepositoryInformationRepository extends JpaRepository<CodeRepositoryInformationPO,Long> {

    /**
     *当前的数量
     * @param repositoryName 仓库名称
     * @param appId 服务appId
     * @param appName APP名称
     * @return 当前的数量
     */
    @Query("select  count(1) from CodeRepositoryInformationPO where repositoryName =?1 " +
            "or appId=?2 or  appName=?3")
    Integer countByRepositoryName(String repositoryName, BigInteger appId, String appName);

    /**
     * 当前api的数量
     * @param apiNo 当前api的数量
     * @return 当前api的数量
     */
    @Query("select  count(1) from CodeRepositoryInformationPO where currApiNo =?1 ")
    Integer countByApiNo(String apiNo);

    /**
     * 获取代码仓库信息
     * @param businessDomainId 通过业务域标识查询 当前的代码仓库信息
     * @return 获取代码仓库信息
     */
    @Query("select po  from CodeRepositoryInformationPO po where po.businessDomainId =?1 ")
    List<CodeRepositoryInformationPO> listByBusinessDomainId(Long businessDomainId);
}
