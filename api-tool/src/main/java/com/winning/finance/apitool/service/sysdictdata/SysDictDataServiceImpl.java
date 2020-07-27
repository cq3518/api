package com.winning.finance.apitool.service.sysdictdata;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.winning.finance.apitool.entity.SysDictDataPO;
import com.winning.finance.apitool.repository.SysDictDataRepository;
import com.winning.finance.apitool.vo.sysdictdata.DictData;
import com.winning.finance.apitool.vo.sysdictdata.DictDateByIdInputVO;
import com.winning.finance.apitool.vo.sysdictdata.DictDateByIdOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 17:04
 */
@Service
public class SysDictDataServiceImpl implements  SysDictDataService {

    @Autowired
    private SysDictDataRepository sysDictDataRepository;

    @Override
    public DictDateByIdOutVO sysDictDataBySysDictId(DictDateByIdInputVO inputVO) {

        List<SysDictDataPO> list= sysDictDataRepository.listByDictId(inputVO.getDictId());

        if(CollectionUtil.isNotEmpty(list)){
            List<DictData> data= Lists.newArrayListWithCapacity(list.size());
            for (SysDictDataPO sysDictDataPO : list) {
                DictData dictData = new DictData();
                BeanUtil.copyProperties(sysDictDataPO, dictData);
                data.add(dictData);

            }
            DictDateByIdOutVO outVO=new DictDateByIdOutVO();
            outVO.setData(data);
            return outVO;
        }
        return null;
    }
}
