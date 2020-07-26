package com.winning.finance.apitool.repository;

import com.winning.finance.apitool.entity.ApiProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/24 16:21
 */
@Repository
public interface ApiProjectRepository extends JpaRepository<ApiProject,Integer> {


}
