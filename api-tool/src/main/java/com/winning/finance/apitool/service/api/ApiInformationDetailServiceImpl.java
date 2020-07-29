package com.winning.finance.apitool.service.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.google.common.collect.Lists;
import com.winning.finance.apitool.base.BusinessException;
import com.winning.finance.apitool.contant.Constant;
import com.winning.finance.apitool.entity.*;
import com.winning.finance.apitool.enumpack.HangUpStatus;
import com.winning.finance.apitool.repository.*;
import com.winning.finance.apitool.vo.apiinfo.save.SaveApiInfoInputVO;
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
            if (Objects.nonNull(updatePO.getApiId())){
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
}
