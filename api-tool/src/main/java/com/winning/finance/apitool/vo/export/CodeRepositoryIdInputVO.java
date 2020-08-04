package com.winning.finance.apitool.vo.export;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/8/4 10:40
 */
@Data
@ApiModel(value = "CodeRepositoryIdInputVO",description = "仓库代码标识")
public class CodeRepositoryIdInputVO {

    /**
     * 仓库代码标识
     */
    @NotNull(message = "代码仓库标识不能为空")
    private Long codeRepositoryId;
}
