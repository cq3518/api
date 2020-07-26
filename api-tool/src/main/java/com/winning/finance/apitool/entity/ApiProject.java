package com.winning.finance.apitool.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/24 16:16
 */
@Data
@Entity
@Table(name="API_PROJECT")

public class ApiProject {


    /**
     * id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//主键生成策略
    @Column(name = "ID")
    private Integer id;


    @Basic
    @Column(name = "PROJECT_NAME")
    private String projectName;


}
