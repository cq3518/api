package com.winning.finance.apitool.service.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.winning.finance.apitool.base.BusinessException;
import com.winning.finance.apitool.contant.Constant;
import com.winning.finance.apitool.entity.*;
import com.winning.finance.apitool.enumpack.ApiState;
import com.winning.finance.apitool.enumpack.HangUpStatus;
import com.winning.finance.apitool.enumpack.ParameterType;
import com.winning.finance.apitool.repository.*;
import com.winning.finance.apitool.util.ApiParamInfoUtil;
import com.winning.finance.apitool.vo.apiinfo.checkout.CheckOutInfoInputVO;
import com.winning.finance.apitool.vo.apiinfo.checkout.CheckOutInfoOutVO;
import com.winning.finance.apitool.vo.apiinfo.info.InfoByGroupIdInputVO;
import com.winning.finance.apitool.vo.apiinfo.info.InfoByGroupIdOutVO;
import com.winning.finance.apitool.vo.apiinfo.save.SaveApiInfoInputVO;
import com.winning.finance.apitool.vo.apiinfo.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.winning.finance.apitool.base.IdWorker.getSnowflakeId;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 16:26
 */
@Service
public class ApiInformationDetailServiceImpl {
    @Autowired
    private ApiInformationDetailUpdateRepository apiInformationDetailUpdateRepository;
    @Autowired
    private ApiChangeLogRepository apiChangeLogRepository;
    @Autowired
    private ApiInformationDetailRepository apiInformationDetailRepository;
    @Autowired
    private ApiParameterInformationUpdateRepository apiParameterInformationUpdateRepository;

    @Autowired
    private ApiParameterInformationRepository apiParameterInformationRepository;

    @Autowired
    private ApiParamInfoUtil apiParamInfoUtil;

    @Transactional(rollbackOn = Exception.class)
    public void save(SaveApiInfoInputVO inputVO) {
        List<Long> hangUpStatusCodes= Lists.newArrayList(HangUpStatus.NEW.getCode(),HangUpStatus.CHECKING_OUT.getCode());
        List<Long> apiUpdateIds= inputVO.getApiUpdateIds();
        List<ApiInformationDetailUpdatePO> apiInformationDetailUpdatePOS=apiInformationDetailUpdateRepository.listByApiUpdateIds(apiUpdateIds,
                hangUpStatusCodes, Constant.IS_DEL_YES);
        //数据校验
        checkData(inputVO,apiInformationDetailUpdatePOS);

        //保存数据
        saveData(inputVO,apiInformationDetailUpdatePOS);


    }

    private void checkData(SaveApiInfoInputVO inputVO,List<ApiInformationDetailUpdatePO> apiInformationDetailUpdatePOS) {
        //1.根据【API修改标识列表】查询【挂起的API修改API_INFORMATION_DETAIL_UPDATE】的修改表【挂起状态代码】in【签出中、新建】，不在则报错
        List<Long> apiUpdateIds= inputVO.getApiUpdateIds();
        List<Long> searchApiUpdateId=apiInformationDetailUpdatePOS.stream().map(e->e.getApiUpdateId()).collect(Collectors.toList());
        // 差集
        List<Long> difference=apiUpdateIds.stream().filter(t-> !searchApiUpdateId.contains(t)).collect(Collectors.toList());
        if(CollectionUtil.isNotEmpty(difference)){
            throw  new BusinessException("传入的api修改标识不在签出中或新建 【api标识】："+difference);
        }
        List<ApiInformationDetailUpdatePO> filter= apiInformationDetailUpdatePOS.stream().filter(e-> !Objects.equals(e.getCreateBy(),inputVO.getSubmitBy())).collect(Collectors.toList());
        if(CollectionUtil.isNotEmpty(filter)){
            throw  new BusinessException("【签出人】不等于【提交人】");
        }

    }


    private void saveData(SaveApiInfoInputVO inputVO,
                         List<ApiInformationDetailUpdatePO> apiInformationDetailUpdatePOS){
        //写入表更记录表
        ApiChangeLogPO changeLogPO=new ApiChangeLogPO();
        long changeId = getSnowflakeId();
        Date now=DateTime.now();
        changeLogPO.setApiChangeId(changeId);
        changeLogPO.setApiChangeBy(inputVO.getSubmitBy());
        changeLogPO.setApiChangeAt(now);
        changeLogPO.setApiChangeReason(inputVO.getApiChangeReason());
        apiChangeLogRepository.save(changeLogPO);
        //参数信息
        List<Long> apiUpdateId=apiInformationDetailUpdatePOS.stream().map(e->e.getApiUpdateId()).collect(Collectors.toList());
        List<ApiParameterInformationUpdatePO> parameterInformationPOs=apiParameterInformationUpdateRepository.listByApiUpdateId(apiUpdateId);
        Map<Long,List<ApiParameterInformationUpdatePO>> map=parameterInformationPOs.stream()
                .collect(Collectors.groupingBy(ApiParameterInformationUpdatePO::getApiUpdateId));

        for (ApiInformationDetailUpdatePO updatePO : apiInformationDetailUpdatePOS) {
            if (Objects.isNull(updatePO.getApiId())){
                //插入
                ApiInformationDetailPO  detailPO=new ApiInformationDetailPO();
                BeanUtil.copyProperties(updatePO, detailPO);
                long apiId = getSnowflakeId();
                detailPO.setApiId(apiId);
                detailPO.setCheckOutAt(now);
                detailPO.setCheckOutBy(inputVO.getSubmitBy());
                apiInformationDetailRepository.save(detailPO);
                List<ApiParameterInformationUpdatePO> updatePOS= map.get(updatePO.getApiUpdateId());
                if(CollectionUtil.isNotEmpty(updatePOS)){

                    updatePOS.forEach(e->{
                        ApiParameterInformationPO  po=new ApiParameterInformationPO();
                        BeanUtil.copyProperties(e, po);
                        po.setApiId(apiId);
                        apiParameterInformationRepository.save(po);
                    });

                }

            }else{
                // 更新
                ApiInformationDetailPO  detailPO=new ApiInformationDetailPO();
                BeanUtil.copyProperties(updatePO, detailPO);
                detailPO.setCheckOutAt(now);
                detailPO.setCheckOutBy(inputVO.getSubmitBy());
                apiInformationDetailRepository.save(detailPO);

                List<ApiParameterInformationUpdatePO> updatePOS= map.get(updatePO.getApiUpdateId());
                if(CollectionUtil.isNotEmpty(updatePOS)){
                    updatePOS.forEach(e->{
                        ApiParameterInformationPO  po=new ApiParameterInformationPO();
                        BeanUtil.copyProperties(e, po);
                        po.setApiId(updatePO.getApiId());
                        apiParameterInformationRepository.save(po);
                    });
                }
            }
           // 更新【挂起的API修改API_INFORMATION_DETAIL_UPDATE】
            apiInformationDetailUpdateRepository.updateStatusByApiUpdateId(updatePO.getApiUpdateId(),HangUpStatus.CHECKING_IN.getCode(),
                    now,changeId);
        }
    }

    public List<InfoByGroupIdOutVO> apiInfoGroupIds(InfoByGroupIdInputVO inputVO) {
        List<InfoByGroupIdOutVO> list=Lists.newArrayList();

        List<ApiInformationDetailPO> apiInformationDetailPOS= apiInformationDetailRepository.listByGroupId(inputVO.getGroupIds(),Constant.IS_DEL_YES);
        List<Long> apiIds=apiInformationDetailPOS.stream().map(e->e.getApiId()).collect(Collectors.toList());
        Map<Long,ApiInformationDetailUpdatePO> map= Maps.newHashMap();
        if(CollectionUtil.isNotEmpty(apiIds)){
            List<ApiInformationDetailUpdatePO> detailUpdatePOS= apiInformationDetailUpdateRepository.ListByIdAndHangUpStatus(apiIds,HangUpStatus.CHECKING_OUT.getCode(),Constant.IS_DEL_YES);
             map=detailUpdatePOS.stream().collect(Collectors.toMap(
                    ApiInformationDetailUpdatePO::getApiId,o->o,(k1,k2)->k2 ));


        }

        for (ApiInformationDetailPO apiInformationDetailPO : apiInformationDetailPOS) {
            InfoByGroupIdOutVO info=new InfoByGroupIdOutVO();
            BeanUtil.copyProperties(apiInformationDetailPO,info);
            ApiInformationDetailUpdatePO updatePO=map.get(apiInformationDetailPO.getApiId());
            if(Objects.nonNull(updatePO)){
                info.setApiUpdateId(updatePO.getApiUpdateId());
                info.setHangUpStatusCode(updatePO.getHangUpStatusCode());
            }
            list.add(info);
        }
        List<ApiInformationDetailUpdatePO> apiInformationDetailUpdatePOS= apiInformationDetailUpdateRepository.listByGroupId(inputVO.getGroupIds(),HangUpStatus.NEW.getCode(),Constant.IS_DEL_YES);
        for (ApiInformationDetailUpdatePO apiInformationDetailUpdatePO : apiInformationDetailUpdatePOS) {
            InfoByGroupIdOutVO info=new InfoByGroupIdOutVO();
            BeanUtil.copyProperties(apiInformationDetailUpdatePO,info);
            list.add(info);
        }
        return list;
    }

    @Transactional(rollbackOn = Exception.class)
    public CheckOutInfoOutVO checkOutApiInfo(CheckOutInfoInputVO inputVO) {

        CheckOutInfoOutVO outInfoOutVO=new CheckOutInfoOutVO();
        //1.根据入参【API标识】调用【Api-12】查询API信息，未查到则报错，如果查到的数据的【API状态代码】=【已废弃】则报错
        ApiInformationDetailPO po= apiInformationDetailRepository.getByIdAndApiState(inputVO.getApiId(), ApiState.OBSOLETE.getCode(),Constant.IS_DEL_YES);
        if(Objects.isNull(po)){
          throw  new BusinessException("api标识【"+inputVO.getApiId()+"】未查到有效的api信息");
        }
        //2.根据【API标识】查询【挂起的API修改API_INFORMATION_DETAIL_UPDATE】的修改表【挂起状态代码】=【签出中】的数据，如果有则报错
        ApiInformationDetailUpdatePO updatePO= apiInformationDetailUpdateRepository.getByIdAndHangUpStatus(inputVO.getApiId(),HangUpStatus.CHECKING_OUT.getCode(),Constant.IS_DEL_YES);
        if(Objects.nonNull(updatePO)){
            throw  new BusinessException("api标识【"+inputVO.getApiId()+"】已经在签出中了！");
        }
        ApiInformationDetailUpdatePO apiInformationDetailUpdatePO=new ApiInformationDetailUpdatePO();
        BeanUtil.copyProperties(po,apiInformationDetailUpdatePO);
        Long apiUpdateId=getSnowflakeId();
        apiInformationDetailUpdatePO.setApiUpdateId(apiUpdateId);
        apiInformationDetailUpdatePO.setHangUpStatusCode(HangUpStatus.CHECKING_OUT.getCode());
        apiInformationDetailUpdatePO.setCreateBy(inputVO.getCheckOutBy());
        apiInformationDetailUpdateRepository.save(apiInformationDetailUpdatePO);
        //api参数插入
        List<ApiParameterInformationPO> parameterInformationPOList= apiParameterInformationRepository.listByApiId(inputVO.getApiId());
        for (ApiParameterInformationPO apiParameterInformationPO : parameterInformationPOList) {
            ApiParameterInformationUpdatePO informationUpdatePO=new ApiParameterInformationUpdatePO();
            BeanUtil.copyProperties(apiParameterInformationPO,informationUpdatePO);
            informationUpdatePO.setApiUpdateId(apiUpdateId);
            apiParameterInformationUpdateRepository.save(informationUpdatePO);
        }

        // 更新 ApiInformationDetailPO
        apiInformationDetailRepository.updateById(inputVO.getApiId(),inputVO.getCheckOutBy(),DateTime.now());

        outInfoOutVO.setApiUpdateId(apiUpdateId);
        return outInfoOutVO;
    }

    public ApiInfoByIdOutVO searchApiInfo(ApiInfoByIdInputVO inputVO) {

        ApiInfoByIdOutVO apiInfoByIdOutVO=new ApiInfoByIdOutVO();

        ApiInformationDetailPO po= apiInformationDetailRepository.getById(inputVO.getApiId(),Constant.IS_DEL_YES);
        if(Objects.isNull(po)){
            throw  new BusinessException("api标识【"+inputVO.getApiId()+"】未查到有效的api信息");
        }
        //赋值到出参
        BeanUtil.copyProperties(po,apiInfoByIdOutVO);
        //查询参数
        List<ApiParameterInformationPO> parameterInformationPOList= apiParameterInformationRepository.listByApiId(inputVO.getApiId());
        //入参
        List<ApiParameter> inputParameters=Lists.newArrayList();
        //出参
        List<ApiParameter> outParameters=Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(parameterInformationPOList)){

            parameterInformationPOList.forEach(e->{

                ApiParameter apiParameter=new ApiParameter();
                BeanUtil.copyProperties(e,apiParameter);
                if(Objects.equals(ParameterType.INPUT_PARAMETER.getCode(),e.getParameterTypeCode())){
                    inputParameters.add(apiParameter);
                }else{
                    outParameters.add(apiParameter);
                }
            });
        }
        apiInfoByIdOutVO.setInputParameterList(apiParamInfoUtil.getApiParameter(inputParameters));
        apiInfoByIdOutVO.setOutputParameterList(apiParamInfoUtil.getApiParameter(outParameters));

        return apiInfoByIdOutVO;
    }

    public ApiInfoByApiUpdateIdOutVO searchApiInfoApiUpdateId(ApiInfoByApiUpdateIdInputVO inputVO) {

        ApiInfoByApiUpdateIdOutVO outVO=new ApiInfoByApiUpdateIdOutVO();
        ApiInformationDetailUpdatePO po= apiInformationDetailUpdateRepository.getById(inputVO.getApiUpdateId(),Constant.IS_DEL_YES);
        if(Objects.isNull(po)){
            throw  new BusinessException("api修改标识【"+inputVO.getApiUpdateId()+"】未查到有效的api信息");
        }
        BeanUtil.copyProperties(po,outVO);
        //查询参数
        List<ApiParameterInformationUpdatePO> parameterInformationPOList= apiParameterInformationUpdateRepository.listByApiUpdateId(Lists.newArrayList(inputVO.getApiUpdateId()));
        //入参
        List<ApiParameter> inputParameters=Lists.newArrayList();
        //出参
        List<ApiParameter> outParameters=Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(parameterInformationPOList)){

            parameterInformationPOList.forEach(e->{

                ApiParameter apiParameter=new ApiParameter();
                BeanUtil.copyProperties(e,apiParameter);
                if(Objects.equals(ParameterType.INPUT_PARAMETER.getCode(),e.getParameterTypeCode())){
                    inputParameters.add(apiParameter);
                }else{
                    outParameters.add(apiParameter);
                }
            });
        }
        outVO.setInputParameterList(apiParamInfoUtil.getApiParameter(inputParameters));
        outVO.setOutputParameterList(apiParamInfoUtil.getApiParameter(outParameters));
        return outVO;
    }
}
