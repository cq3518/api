package com.winning.finance.apitool.controller;

import com.winning.finance.apitool.base.ResponseResult;
import com.winning.finance.apitool.contant.ApiPathConstant;
import com.winning.finance.apitool.service.codegroup.CodeGroupService;
import com.winning.finance.apitool.vo.coderepositorygroup.add.AddGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.add.AddGroupOutVO;
import com.winning.finance.apitool.vo.coderepositorygroup.delete.DeleteGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.edit.EditGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.edit.EditGroupOutVO;
import com.winning.finance.apitool.vo.coderepositorygroup.search.SearchGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.search.SearchGroupOutVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>api-tool</p>
 * 仓库包含的分组信息
 * @author cq
 * @Description
 * @date 2020/7/28 13:35
 */
@RestController
@Api(tags = "分组信息管理")
public class CodeRepositoryGroupController {


    @Autowired
    private CodeGroupService codeGroupService;

    @ApiOperation(nickname = "新增分组信息", value = "新增分组信息")
    @PostMapping(ApiPathConstant.CODE_REPOSITORY_GROUP_ADD)
    public ResponseResult codeRepositoryGroupAdd(@Valid @RequestBody AddGroupInputVO inputVO) {

        AddGroupOutVO outVO=codeGroupService.add(inputVO);

        return ResponseResult.success(outVO);
    }
    @ApiOperation(nickname = "修改分组信息", value = "修改分组信息")
    @PostMapping(ApiPathConstant.CODE_REPOSITORY_GROUP_EDIT)
    public ResponseResult codeRepositoryGroupEdit(@Valid @RequestBody EditGroupInputVO inputVO) {

        EditGroupOutVO outVO=codeGroupService.edit(inputVO);

        return ResponseResult.success(outVO);
    }
    @ApiOperation(nickname = "删除分组信息", value = "删除分组信息")
    @PostMapping(ApiPathConstant.CODE_REPOSITORY_GROUP_DELETE)
    public ResponseResult codeRepositoryGroupDelete(@Valid @RequestBody DeleteGroupInputVO inputVO) {

        codeGroupService.delete(inputVO);

        return ResponseResult.success(null);
    }

    @ApiOperation(nickname = "根据代码仓库标识查询分组信息", value = "根据代码仓库标识查询分组信息")
    @PostMapping(ApiPathConstant.CODE_REPOSITORY_GROUP_SEARCH)
    public ResponseResult codeRepositoryGroupSearch(@Valid @RequestBody SearchGroupInputVO inputVO) {

        SearchGroupOutVO outVO= codeGroupService.search(inputVO);

        return ResponseResult.success(outVO);
    }
}
