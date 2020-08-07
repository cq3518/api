package com.winning.finance.apitool.service.coderepository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.winning.finance.apitool.base.BusinessException;
import com.winning.finance.apitool.contant.Constant;
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
import com.winning.finance.apitool.vo.coderepository.getappid.AppIdByDomainIdInputVO;
import com.winning.finance.apitool.vo.coderepository.getappid.AppIdByDomainIdOutVO;
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

        // 判断 服务appId 四位
        if(inputVO.getAppId().toString().length()!=4){
            throw new BusinessException("服务appId为四位数");
        }

        Long businessDomainId=inputVO.getBusinessDomainId();
        //通过业务域 查询当前的业务域编码
        BusinessDomainInformationPO po=domainInformationRepository.getByDomainId(businessDomainId);
        if(Objects.isNull(po)){
            throw new BusinessException("当前业务域标识【"+businessDomainId+"】未查询到业务域信息");
        }
        if( !NumberUtil.isNumber(po.getBusinessDomainRepositoryNo())){
            throw  new BusinessException("当前的业务域编码不是数字类型！！");

        }
        //获取当前最大的appId
        String appIdString=inputVO.getRepositoryArchitectTypeCode()+po.getBusinessDomainRepositoryNo();
        List<CodeRepositoryInformationPO> list= codeRepository.listbyAppId(appIdString);
        if(CollectionUtil.isEmpty(list)){

            if(!(appIdString+Constant.STRING_ZERO).equals(inputVO.getAppId().toString())){
                throw  new BusinessException("appId应为："+appIdString+Constant.STRING_ZERO);
            }
        }else{
            int appId=list.get(0).getAppId().intValue()+1;
            if(appId!=inputVO.getAppId().intValue()){
                throw new BusinessException("appId应为："+appId);
            }

        }
       //校验
        if(!inputVO.getExceptionClassNo().toString().equals(inputVO.getAppId().toString())){
            throw new BusinessException("异常类别编码和服务appId一致");
        }
        if(!(inputVO.getDefaultPort()+Constant.STRING_ZERO).equals(inputVO.getAppId().toString())){
            throw new BusinessException("默认端口号应为："+inputVO.getAppId()+Constant.STRING_ZERO);
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
        checkApiNo(inputVO);
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
        if(StrUtil.isBlank(inputVO.getMemo())){
            po.setMemo(oldPO.getMemo());
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

    private void checkApiNo(EditInputVO inputVO){

        int count=codeRepository.countByApiNo(inputVO.getCurrApiNo(),inputVO.getCodeRepositoryId());
        if(count>0){
            throw  new BusinessException("已存在【api编号】："+inputVO.getCurrApiNo());
        }
        // 判断 服务appId 四位
        if(inputVO.getAppId().toString().length()!=4){
            throw new BusinessException("服务appId为四位数");
        }

        Long businessDomainId=inputVO.getBusinessDomainId();
        //通过业务域 查询当前的业务域编码
        BusinessDomainInformationPO po=domainInformationRepository.getByDomainId(businessDomainId);
        if(Objects.isNull(po)){
            throw new BusinessException("当前业务域标识【"+businessDomainId+"】未查询到业务域信息");
        }
        if( !NumberUtil.isNumber(po.getBusinessDomainRepositoryNo())){
            throw  new BusinessException("当前的业务域编码不是数字类型！！");

        }
        //获取当前最大的appId
        String appIdString=inputVO.getRepositoryArchitectTypeCode()+po.getBusinessDomainRepositoryNo();
        List<CodeRepositoryInformationPO> list= codeRepository.listbyAppId(appIdString);
        if(CollectionUtil.isEmpty(list)){

            if(!(appIdString+Constant.STRING_ZERO).equals(inputVO.getAppId().toString())){
                throw  new BusinessException("appId应为："+appIdString+Constant.STRING_ZERO);
            }
        }else{
            int appId=list.get(0).getAppId().intValue()+1;
            if(appId!=inputVO.getAppId().intValue()){
                throw new BusinessException("appId应为："+appId);
            }

        }
        //校验
        if(!inputVO.getExceptionClassNo().toString().equals(inputVO.getAppId().toString())){
            throw new BusinessException("异常类别编码和服务appId一致");
        }
        if(!(inputVO.getDefaultPort()+Constant.STRING_ZERO).equals(inputVO.getAppId().toString())){
            throw new BusinessException("默认端口号应为："+inputVO.getAppId()+Constant.STRING_ZERO);
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


    @Override
    public AppIdByDomainIdOutVO appIdByDomainIdRepositoryArchitectTypeCode(AppIdByDomainIdInputVO inputVO) {

        Long architectTypeCode=inputVO.getRepositoryArchitectTypeCode();
        if(architectTypeCode.intValue()>4 || architectTypeCode.intValue()<1){

            throw new BusinessException("仓库架构类别代码在区间[1,4]之间");
        }

        AppIdByDomainIdOutVO outVO=new  AppIdByDomainIdOutVO();
        //通过业务域 查询当前的业务域编码
        BusinessDomainInformationPO po=domainInformationRepository.getByDomainId(inputVO.getBusinessDomainId());
        if(Objects.isNull(po)){
            throw new BusinessException("当前业务域标识【"+inputVO.getBusinessDomainId()+"】未查询到业务域信息");
        }
        if( !NumberUtil.isNumber(po.getBusinessDomainRepositoryNo())){
            throw  new BusinessException("当前的业务域编码不是数字类型！！");
        }
        //查询 代码仓库中的 最大值是？ 通过当前仓库架构类别代码+业务域编码查询
        String appIdString=inputVO.getRepositoryArchitectTypeCode()+po.getBusinessDomainRepositoryNo();
        List<CodeRepositoryInformationPO> list= codeRepository.listbyAppId(appIdString);
        Integer appId= 0;
        if(CollectionUtil.isEmpty(list)){
            appId= Integer.parseInt(appIdString+ Constant.STRING_ZERO);

        }else{
            appId=list.get(0).getAppId().intValue()+1;
        }
        outVO.setAppId(appId);
        outVO.setExceptionClassNo(appId);
        outVO.setDefaultPort(Integer.parseInt(appId+"0"));
        return outVO;
    }
}
