package com.winning.finance.apitool.service.impl;

import com.winning.finance.apitool.base.BusinessException;
import com.winning.finance.apitool.base.ResultCode;
import com.winning.finance.apitool.entity.ApiProject;
import com.winning.finance.apitool.repository.ApiProjectRepository;
import com.winning.finance.apitool.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/24 16:25
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private ApiProjectRepository apiProjectRepository;

    @Override
    public String getName(Integer id) {

           ApiProject apiProject= apiProjectRepository.getOne(id);

          if(Objects.nonNull(apiProject)){
                return apiProject.getProjectName();
          }
        return null;
    }
}
