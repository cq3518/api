package com.winning.finance.apitool.service.sysdictdata;

import com.winning.finance.apitool.vo.sysdictdata.DictDateByIdInputVO;
import com.winning.finance.apitool.vo.sysdictdata.DictDateByIdOutVO;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 17:04
 */
public interface SysDictDataService {
    /**
     * 根据字典主键查询字典数据信息列表
     * @param inputVO 根据字典主键查询字典数据信息列表
     * @return 根据字典主键查询字典数据信息列表
     */
    DictDateByIdOutVO sysDictDataBySysDictId(DictDateByIdInputVO inputVO);
}
