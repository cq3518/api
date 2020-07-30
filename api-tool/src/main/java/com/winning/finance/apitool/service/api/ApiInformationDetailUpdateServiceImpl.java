package com.winning.finance.apitool.service.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.winning.finance.apitool.base.BusinessException;
import com.winning.finance.apitool.contant.Constant;
import com.winning.finance.apitool.entity.ApiInformationDetailUpdatePO;
import com.winning.finance.apitool.entity.ApiParameterInformationUpdatePO;
import com.winning.finance.apitool.enumpack.HangUpStatus;
import com.winning.finance.apitool.enumpack.ParameterType;
import com.winning.finance.apitool.repository.ApiInformationDetailRepository;
import com.winning.finance.apitool.repository.ApiInformationDetailUpdateRepository;
import com.winning.finance.apitool.repository.ApiParameterInformationUpdateRepository;
import com.winning.finance.apitool.vo.apiinfo.delete.DeleteHangUpInputVO;
import com.winning.finance.apitool.vo.apiinfo.edit.EditHangUpInputVO;
import com.winning.finance.apitool.vo.apiinfo.edit.EditHangUpOutVO;
import com.winning.finance.apitool.vo.apiinfo.hangup.NewHangUpInputVO;
import com.winning.finance.apitool.vo.apiinfo.hangup.NewHangUpOutVO;
import com.winning.finance.apitool.vo.apiinfo.hangup.ParameterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.winning.finance.apitool.base.IdWorker.getSnowflakeId;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 11:01
 */
@Service
public class ApiInformationDetailUpdateServiceImpl {


    @Autowired
    private ApiInformationDetailUpdateRepository apiInformationDetailUpdateRepository;

    @Autowired
    private ApiInformationDetailRepository apiInformationDetailRepository;

    @Autowired
    private ApiParameterInformationUpdateRepository parameterInformationUpdateRepository;


    @Transactional(rollbackOn = Exception.class)
    public NewHangUpOutVO hangUp(NewHangUpInputVO inputVO) {

        NewHangUpOutVO outVO =new NewHangUpOutVO();
        // 1.查询 API_INFORMATION_DETAIL，判断是否存在【API名称】或【API的URL】或 【api编码】相同的API，如果存在则报错。
        int count= apiInformationDetailRepository.countByApiInfo(inputVO.getApiName(),inputVO.getApiUrl(),inputVO.getApiNo());
        if(count>0){
            throw  new BusinessException("存在【API名称】:"+inputVO.getApiName()+"或【API的URL】:"+inputVO.getApiUrl()+
                    "或 【api编码】:"+inputVO.getApiNo()+"相同的API");
        }

        ApiInformationDetailUpdatePO po=new ApiInformationDetailUpdatePO();
        //api修改标识
        long apiUpdateId = getSnowflakeId();
        BeanUtil.copyProperties(inputVO, po);
        po.setApiUpdateId(apiUpdateId);
        po.setIsDel(Constant.IS_DEL_YES);
        Date now=DateTime.now();
        po.setCreateAt(now);
        po.setModifiedAt(now);
        po.setHangUpStatusCode(HangUpStatus.NEW.getCode());
        apiInformationDetailUpdateRepository.save(po);

        // api参数列表(入参) 树形结构
        saveParameter(apiUpdateId, inputVO.getInputParameterList(),0L, ParameterType.INPUT_PARAMETER.getCode());
        // api参数列表(出参) 树形结构
        saveParameter(apiUpdateId, inputVO.getOutputParameterList(),0L, ParameterType.OUT_PARAMETER.getCode());

        outVO.setApiUpdateId(apiUpdateId);
        return outVO;
    }

    /**
     * 入参 和出参类型代码 保存
     * @param apiUpdateId
     * @param inputParameterList
     * @param parentParameterId
     * @param parameterTypeCode
     */
    private void saveParameter(long apiUpdateId,
                               List<ParameterVO> inputParameterList,
                               Long parentParameterId,
                               Long parameterTypeCode) {

        if(CollectionUtil.isNotEmpty(inputParameterList)){
            for (ParameterVO parameterVO : inputParameterList) {

                ApiParameterInformationUpdatePO parameterPO=new ApiParameterInformationUpdatePO();
                long parameterId = getSnowflakeId();
                BeanUtil.copyProperties(parameterVO, parameterPO);
                //主键id
                parameterPO.setApiParameterId(parameterId);
                parameterPO.setApiUpdateId(apiUpdateId);
                parameterPO.setApiId(null);
                parameterPO.setParameterTypeCode(parameterTypeCode);
                parameterPO.setParentParameterId(parentParameterId);
                parameterInformationUpdateRepository.save(parameterPO);

                List<ParameterVO> list= parameterVO.getParameterVOS();

                saveParameter(apiUpdateId,list,parameterId,parameterTypeCode);
            }

        }
    }


    @Transactional(rollbackOn = Exception.class)
    public EditHangUpOutVO editHangUp(EditHangUpInputVO inputVO) {

        EditHangUpOutVO outVO=new EditHangUpOutVO();
        Long apiUpdateId= inputVO.getApiUpdateId();
        //更新挂起的api表
        Optional<ApiInformationDetailUpdatePO> optional= apiInformationDetailUpdateRepository.findById(apiUpdateId);
        if(!optional.isPresent()){
            throw  new BusinessException("API修改标识【"+apiUpdateId+"】未查到数据!");
        }
        ApiInformationDetailUpdatePO oldPo=optional.get();
        ApiInformationDetailUpdatePO po=new ApiInformationDetailUpdatePO();
        BeanUtil.copyProperties(inputVO, po);

        po.setApiChangeId(oldPo.getApiChangeId());
        po.setGroupId(oldPo.getGroupId());
        po.setApiNo(oldPo.getApiNo());
        po.setCreateAt(oldPo.getCreateAt());
        po.setModifiedAt(DateTime.now());
        if(StrUtil.isBlank(inputVO.getMemo())){
            po.setMemo(oldPo.getMemo());
        }
        po.setIsDel(Constant.IS_DEL_YES);
        po.setHangUpStatusCode(oldPo.getHangUpStatusCode());
        po.setCheckInAt(oldPo.getCheckInAt());
        po.setCreateBy(oldPo.getCreateBy());
        apiInformationDetailUpdateRepository.save(po);
        //删除 参数列表
        parameterInformationUpdateRepository.deleteByApiUpdateId(apiUpdateId);
        // api参数列表(入参) 树形结构
        saveParameter(apiUpdateId, inputVO.getInputParameterList(),0L, ParameterType.INPUT_PARAMETER.getCode());
        // api参数列表(出参) 树形结构
        saveParameter(apiUpdateId, inputVO.getOutputParameterList(),0L, ParameterType.OUT_PARAMETER.getCode());
        outVO.setApiUpdateId(apiUpdateId);
        return outVO;

    }

    public void deleteHangUp(DeleteHangUpInputVO inputVO) {

        //1.根据【API修改标识列表】查询【挂起的API修改API_INFORMATION_DETAIL_UPDATE】的修改表【挂起状态代码】in【签出中、新建】，不在则报错
        List<Long> hangUpStatusCode= Lists.newArrayList(HangUpStatus.CHECKING_OUT.getCode(),HangUpStatus.NEW.getCode());
        List<ApiInformationDetailUpdatePO> list=apiInformationDetailUpdateRepository.listByApiUpdateIds(inputVO.getApiUpdateIds(),
                hangUpStatusCode,Constant.IS_DEL_YES);
        List<Long> apiUpdateIds=list.stream().map(e->e.getApiUpdateId()).collect(Collectors.toList());
        // 差集
        List<Long> difference=inputVO.getApiUpdateIds().stream().filter(t-> !apiUpdateIds.contains(t)).collect(Collectors.toList());
        if(CollectionUtil.isNotEmpty(difference)){
            throw  new BusinessException("传入的api修改标识不在签出中或新建 【api标识】："+difference);
        }

        // 更新
        apiInformationDetailUpdateRepository.updateByApiUpdateIds(inputVO.getApiUpdateIds(),HangUpStatus.RESCINDED.getCode(),DateTime.now());
    }
}
