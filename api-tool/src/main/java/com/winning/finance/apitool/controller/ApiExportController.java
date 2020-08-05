package com.winning.finance.apitool.controller;

import com.google.common.collect.Maps;
import com.winning.finance.apitool.base.WordUtil;
import com.winning.finance.apitool.service.export.ApiExportServiceImpl;
import com.winning.finance.apitool.vo.export.CodeRepositoryIdInputVO;
import com.winning.finance.apitool.vo.export.CodeRepositoryIdOutVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Value("${file.upload}")
    private String upload;

    @PostMapping("export/codeRepositoryId")
    @ApiOperation(value="通过仓库标识导出doc", httpMethod = "POST",notes = "通过仓库标识导出doc")
    public void   exportByCodeRepositoryId(HttpServletRequest request, @Valid @RequestBody CodeRepositoryIdInputVO inputVO, HttpServletResponse response) {

         List<CodeRepositoryIdOutVO> codeRepositoryIdOutVOS=  apiExportService.exportByCodeRepositoryId(inputVO.getCodeRepositoryId());

         String  name=apiExportService.repositoryNameById(inputVO.getCodeRepositoryId());
         Map<String,Object> map= Maps.newHashMap();

        map.put("codeRepositoryIdOutVOS",codeRepositoryIdOutVOS);
        WordUtil.createWord(map,"codeRepositoryId.ftl",upload+name+".doc");
        WordUtil.downLoadFile(request,upload+name+".doc",response);
        // 删除文件
       // WordUtil.DeleteFolder(upload+name+".doc");
    }
}


