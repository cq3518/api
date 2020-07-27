package com.winning.finance.apitool.vo.coderepository.domain;

import lombok.Data;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/27 14:55
 */
@Data
public class DoMainInfoVO {
    /**
     * 业务域标识
     */
    private Long businessDomainId;
    /**
     * 业务域编码
     */
    private String businessDomainRepositoryNo;

    /**
     * 业务域名称
     */
    private String businessDomainName;
}
