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
     * 3.1.1 查询业务域信息
     */
    public  static final String BUSINESS_DOMAIN_INFORMATION_SEARCH=PRE+"/businessDomainInformation/search";

    /**
     * 3.1.2 根据业务域编码查询代码仓库信息
     */
    public  static final String CODE_REPOSITORY_INFORMATION_SEARCH=PRE+"/codeRepositoryInformation/search";

    /**
     * 3.1.3 代码仓库 新增api
     */
    public  static final String CODE_REPOSITORY_INFORMATION_ADD=PRE+"/codeRepositoryInformation/add";
    /**
     * 3.1.4 代码仓库 编辑api
     */
    public  static final String CODE_REPOSITORY_INFORMATION_EDIT=PRE+"/codeRepositoryInformation/edit";

    /**
     * 3.4.1 根据字典主键查询字典数据信息列表
     */
    public  static final String SYS_DICT_DATA_BY_SYS_DICT_ID=PRE+"/sysDictData/dictDataById";
    /**
     * 3.2.2 新增分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_ADD=PRE+"/codeRepositoryGroup/add";

    /**
     *  3.2.3 修改分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_EDIT=PRE+"/codeRepositoryGroup/edit";

    /**
     *  3.2.4 删除分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_DELETE=PRE+"/codeRepositoryGroup/delete";

    /**
     * 3.2.1   根据代码仓库标识查询分组信息
     */
    public  static final String CODE_REPOSITORY_GROUP_SEARCH=PRE+"/codeRepositoryGroup/search";

    /**
     *3.3.2 新建挂起的API信息 挂起的API（API_INFORMATION_DETAIL_UPDATE）
     */
    public  static final String API_INFORMATION_DETAIL_UPDATE_HANG_UP=PRE+"/apiInformationDetailUpdate/hangUp";

    /**
     *3.3.4  修改挂起的API信息 edit
     */
    public  static final String API_INFORMATION_DETAIL_UPDATE_HANG_UP_EDIT=PRE+"/apiInformationDetailUpdate/editHangUp";
    /**
     * 3.3.5 撤销挂起的API修改
     */
    public  static final String API_INFORMATION_DETAIL_UPDATE_HANG_UP_DELETE=PRE+"/apiInformationDetailUpdate/deleteHangUp";
    /**
     * 3.3.7提交挂起的API修改
     */
    public  static final String API_INFORMATION_DETAIL_SAVE=PRE+"/apiInformationDetail/save";

    /**
     * 3.3.1 根据分组标识列表查询API信息列表
     */
    public  static final String API_INFO_GROUP_IDS=PRE+"/apiInformation/infoByGroupIds";
    /**
     * 3.3.3 签出API信息以进行修改
     */
    public static final String CHECK_OUT_API_INFO=PRE+"/apiInformation/checkOutApiInfo";
    /**
     * 3.3.6 根据API标识查询API信息 （api_info信息）
     */
    public static final String SEARCH_API_INFO=PRE+"/apiInformation/searchApiInfo";

    /**
     * 3.3.7 根据签出API标识查询签出的API信息---new
     */
    public static final String SEARCH_API_INFO_API_UPDATE_ID=PRE+"/apiInformation/searchApiInfoByUpdate";

    /**
     * 根据业务域标识、架构类别代码获取当前可用的appId
     */
    public static final String APP_ID_BY_DOMAIN_ID_REPOSITORY_ARCHITECT_TYPE_CODE=PRE+"/codeRepositoryInformation/appIdByDomainId";
}
