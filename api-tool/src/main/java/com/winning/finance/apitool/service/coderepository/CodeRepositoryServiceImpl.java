package com.winning.finance.apitool.service.coderepository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.winning.finance.apitool.base.BusinessException;
import com.winning.finance.apitool.entity.BusinessDomainInformationPO;
import com.winning.finance.apitool.entity.CodeRepositoryInformationPO;
import com.winning.finance.apitool.repository.BusinessDomainInformationRepository;
import com.winning.finance.apitool.repository.CodeRepositoryInformationRepository;
import com.winning.finance.apitool.vo.coderepository.add.AddInputVO;
import com.winning.finance.apitool.vo.coderepository.add.AddOutVO;
import com.winning.finance.apitool.vo.coderepository.domain.DoMainInfoVO;
import com.winning.finance.apitool.vo.coderepository.domain.QueryAllOutVO;
import com.winning.finance.apitool.vo.coderepository.edit.EditInputVO;
import com.winning.finance.apitool.vo.coderepository.edit.EditOutVO;
import com.winning.finance.apitool.vo.coderepository.search.CodeRepositoryVO;
import com.winning.finance.apitool.vo.coderepository.search.SearchByIdInputVO;
import com.winning.finance.apitool.vo.coderepository.search.SearchByIdOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.winning.finance.apitool.base.IdWorker.getSnowflakeId;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 13:20
 */
@Service
public class CodeRepositoryServiceImpl implements CodeRepositoryService {

    @Autowired
    private CodeRepositoryInformationRepository codeRepository;

    @Autowired
    private BusinessDomainInformationRepository domainInformationRepository;

    @Override
    public AddOutVO add(AddInputVO inputVO) {
        // 校验数据
        checkDate(inputVO);
        AddOutVO outVO =new AddOutVO();
        //生成主键id
        long id = getSnowflakeId();
        CodeRepositoryInformationPO po=new CodeRepositoryInformationPO();
        BeanUtil.copyProperties(inputVO, po);
        po.setCodeRepositoryId(id);
        outVO.setCodeRepositoryId(id);
        Date date=DateTime.now();
        po.setCreateAt(date);
        po.setModifiedAt(date);
        codeRepository.save(po);
        return outVO;
    }

    private void checkDate(AddInputVO inputVO){

        int count=codeRepository.countByRepositoryName(inputVO.getRepositoryName(),inputVO.getAppId(),inputVO.getAppName());
        if(count>0){
            throw  new BusinessException("已存在【仓库名称】或【服务appId】或【APP名称】相同的代码仓库");
        }

    }

    @Override
    public QueryAllOutVO searchBusinessDomain() {

        List<BusinessDomainInformationPO> list=domainInformationRepository.findAll();
        if(CollectionUtil.isNotEmpty(list)){
            final List<DoMainInfoVO> data= Lists.newArrayListWithCapacity(list.size());
            list.forEach(e->{
                DoMainInfoVO doMainInfoVO=new DoMainInfoVO();
                BeanUtil.copyProperties(e, doMainInfoVO);
                data.add(doMainInfoVO);
            });
            QueryAllOutVO queryAllOutVO=new QueryAllOutVO();
            queryAllOutVO.setData(data);
            return  queryAllOutVO;
        }
        return null;
    }

    @Override
    public EditOutVO edit(EditInputVO inputVO) {
        EditOutVO editOutVO=new EditOutVO();
        editOutVO.setCodeRepositoryId(inputVO.getCodeRepositoryId());
        //当api编号存在时 抛出错误信息
        checkApiNo(inputVO.getCurrApiNo());
        // 修改
        CodeRepositoryInformationPO oldPO=  getPO(inputVO);
        CodeRepositoryInformationPO po=new CodeRepositoryInformationPO();
        BeanUtil.copyProperties(inputVO, po);
        Date date=DateTime.now();
        po.setModifiedAt(date);
        po.setCreateAt(oldPO.getCreateAt());
        po.setCreateBy(oldPO.getCreateBy());
        if(Objects.isNull(inputVO.getRepositoryBusinessTypeCode())){
            po.setRepositoryArchitectTypeCode(oldPO.getRepositoryBusinessTypeCode());
        }
        if(StrUtil.isBlank(inputVO.getInterfaceBasePath())){
            po.setInterfaceBasePath(oldPO.getInterfaceBasePath());
        }
        if(StrUtil.isBlank(inputVO.getExplain())){
            po.setExplain(oldPO.getExplain());
        }
        codeRepository.save(po);
        return editOutVO;
    }

    private CodeRepositoryInformationPO getPO(EditInputVO inputVO) {
        Optional<CodeRepositoryInformationPO> optional= codeRepository.findById(inputVO.getCodeRepositoryId());
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw  new BusinessException("没有【代码仓库标识】："+inputVO.getCodeRepositoryId());
        }
    }

    private void checkApiNo(String apiNo){

        int count=codeRepository.countByApiNo(apiNo);
        if(count>0){
            throw  new BusinessException("已存在【api编号】："+apiNo);
        }

    }

    @Override
    public SearchByIdOutVO search(SearchByIdInputVO inputVO) {

        List<CodeRepositoryInformationPO> list= codeRepository.listByBusinessDomainId(inputVO.getBusinessDomainId());
        if(CollectionUtil.isNotEmpty(list)){

            final List<CodeRepositoryVO> data= Lists.newArrayListWithCapacity(list.size());
            list.forEach(e->{
                CodeRepositoryVO codeRepositoryVO=new CodeRepositoryVO();
                BeanUtil.copyProperties(e, codeRepositoryVO);
                data.add(codeRepositoryVO);
            });
            SearchByIdOutVO queryAllOutVO=new SearchByIdOutVO();
            queryAllOutVO.setData(data);
            return  queryAllOutVO;

        }
        return null;
    }
}
