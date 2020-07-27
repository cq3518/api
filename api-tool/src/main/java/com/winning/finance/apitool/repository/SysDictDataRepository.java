package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.SysDictDataPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 16:47
 */
@Repository
public interface SysDictDataRepository extends JpaRepository<SysDictDataPO,Long> {


    /**
     *字典数据
     * @param dictId 字典主键
     * @return  字典数据
     */
    @Query("select po  from SysDictDataPO po where po.dictId =?1   ORDER BY po.dictDataSort DESC")
    List<SysDictDataPO> listByDictId(Long dictId);
}
