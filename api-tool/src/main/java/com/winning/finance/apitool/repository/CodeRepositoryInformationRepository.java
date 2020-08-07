package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.CodeRepositoryInformationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    @Query("select  count(1) from CodeRepositoryInformationPO where currApiNo =?1 and codeRepositoryId <> ?2")
    Integer countByApiNo(String apiNo,Long codeRepositoryId);

    /**
     * 获取代码仓库信息
     * @param businessDomainId 通过业务域标识查询 当前的代码仓库信息
     * @return 获取代码仓库信息
     */
    @Query("select po  from CodeRepositoryInformationPO po where po.businessDomainId =?1 ")
    List<CodeRepositoryInformationPO> listByBusinessDomainId(Long businessDomainId);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(" update   CodeRepositoryInformationPO set currApiNo=?2 where codeRepositoryId=?1")
    void updateApiNoById(Long codeRepositoryId, String currApiNoUpdate);


    @Query("select po  from CodeRepositoryInformationPO po where po.appId like '%?1%' order by appId desc ")
    List<CodeRepositoryInformationPO> listbyAppId(String appId);
}
