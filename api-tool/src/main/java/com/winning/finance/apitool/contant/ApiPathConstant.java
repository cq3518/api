package com.winning.finance.apitool.contant;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 11:17
 */
public class ApiPathConstant {


    /**
     * 查询业务域信息
     */
    public  static final String BUSINESS_DOMAIN_INFORMATION_SEARCH="/businessDomainInformation/search";

    /**
     * 根据业务域编码查询代码仓库信息
     */
    public  static final String CODE_REPOSITORY_INFORMATION_SEARCH="/codeRepositoryInformation/search";

    /**
     * 代码仓库 新增api
     */
    public  static final String CODE_REPOSITORY_INFORMATION_ADD="/codeRepositoryInformation/add";
    /**
     * 代码仓库 编辑api
     */
    public  static final String CODE_REPOSITORY_INFORMATION_EDIT="/codeRepositoryInformation/edit";

    /**
     *  根据字典主键查询字典数据信息列表
     */
    public  static final String SYS_DICT_DATA_BY_SYS_DICT_ID="/sysDictData/dictDataById";
}
