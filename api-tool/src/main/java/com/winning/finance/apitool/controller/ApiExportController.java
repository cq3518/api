package com.winning.finance.apitool.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.winning.finance.apitool.base.WordUtil;
import com.winning.finance.apitool.service.export.ApiExportServiceImpl;
import com.winning.finance.apitool.vo.export.CodeRepositoryIdInputVO;
import com.winning.finance.apitool.vo.export.CodeRepositoryIdOutVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/8/3 16:01
 */
@RestController
@Api(tags = "api导出")
public class ApiExportController {


    @Autowired
    private ApiExportServiceImpl apiExportService;


    @PostMapping("export/test")
    @ApiOperation(value="导出doc", httpMethod = "POST",notes = "导出doc")
    public void   exportByCodeRepositoryId(@Valid @RequestBody CodeRepositoryIdInputVO inputVO, HttpServletResponse response) {

        List<CodeRepositoryIdOutVO> codeRepositoryIdOutVOS=  apiExportService.exportByCodeRepositoryId(inputVO.getCodeRepositoryId());

         Map<String,Object> map= Maps.newHashMap();

        map.put("codeRepositoryIdOutVOS",codeRepositoryIdOutVOS);
        WordUtil.createWord(map,"codeRepositoryId.ftl","E:/word/codeRepositoryId.doc");
        WordUtil.downLoadFile("E:/word/codeRepositoryId.doc",response);

    }
}


