package com.winning.finance.apitool.vo.sysdictdata;

import lombok.Data;

import java.util.Date;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 16:57
 */
@Data
public class DictData {
    /**
     *字典数据主键
     */
    private Long dictDataId;
    /**
     *字典数据说明
     */
    private String dictDataDesc;

    /**
     *是否默认
     */
    private Integer isDefault;
    /**
     *状态
     */
    private String status;
    /**
     *创建人
     */
    private String createBy;
    /**
     *创建时间
     */
    private Date createAt;
    /**
     *最后更新时间
     */
    private Date modifiedAt;

}
