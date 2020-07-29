package com.winning.finance.apitool.contant;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 11:17
 */
public class ApiPathConstant {


    private static final String PRE="/apiTool";
    /**
     * 查询业务域信息
     */
    public  static final String BUSINESS_DOMAIN_INFORMATION_SEARCH=PRE+"/businessDomainInformation/search";

    /**
     * 根据业务域编码查询代码仓库信息
     */
    public  static final String CODE_REPOSITORY_INFORMATION_SEARCH=PRE+"/codeRepositoryInformation/search";

    /**
     * 代码仓库 新增api
     */
    public  static final String CODE_REPOSITORY_INFORMATION_ADD=PRE+"/codeRepositoryInformation/add";
    /**
     * 代码仓库 编辑api
     */
    public  static final String CODE_REPOSITORY_INFORMATION_EDIT=PRE+"/codeRepositoryInformation/edit";

    /**
     *  根据字典主键查询字典数据信息列表
     */
    public  static final String SYS_DICT_DATA_BY_SYS_DICT_ID=PRE+"/sysDictData/dictDataById";
    /**
     *   新增分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_ADD=PRE+"/codeRepositoryGroup/add";

    /**
     *   修改分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_EDIT=PRE+"/codeRepositoryGroup/edit";

    /**
     *   删除分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_DELETE=PRE+"/codeRepositoryGroup/delete";

    /**
     *    根据代码仓库标识查询分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_SEARCH=PRE+"/codeRepositoryGroup/search";

    /**
     *3.1.2 新建挂起的API信息 挂起的API（API_INFORMATION_DETAIL_UPDATE）
     */
    public  static final String API_INFORMATION_DETAIL_UPDATE_HANG_UP=PRE+"/apiInformationDetailUpdate/hangUp";

    /**
     *3.1.4  修改挂起的API信息 edit
     */
    public  static final String API_INFORMATION_DETAIL_UPDATE_HANG_UP_EDIT=PRE+"/apiInformationDetailUpdate/editHangUp";
    /**
     * 3.1.5 撤销挂起的API修改
     */
    public  static final String API_INFORMATION_DETAIL_UPDATE_HANG_UP_DELETE=PRE+"/apiInformationDetailUpdate/deleteHangUp";
    /**
     * 3.1.7提交挂起的API修改
     */
    public  static final String API_INFORMATION_DETAIL_SAVE=PRE+"/apiInformationDetail/save";
}
