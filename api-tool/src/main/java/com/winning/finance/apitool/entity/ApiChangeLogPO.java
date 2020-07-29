package com.winning.finance.apitool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 16:43
 */
@Entity
@Data
@Table(name="API_CHANGE_LOG")
public class ApiChangeLogPO {

    /**
     * API变更标识
     */
    @Id
    @Column(name = "API_CHANGE_ID")
    private Long apiChangeId;

    /**
     * API变更缘由
     */
    @Basic
    @Column(name = "API_CHANGE_REASON")
    private String apiChangeReason;

    /**
     * API变更时间
     */
    @Basic
    @Column(name = "API_CHANGE_AT")
    private Date apiChangeAt;

    /**
     * API变更人
     */
    @Basic
    @Column(name = "API_CHANGE_BY")
    private String apiChangeBy;
}
