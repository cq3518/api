package com.winning.finance.apitool.controller;


import com.winning.finance.apitool.base.ResponseResult;
import com.winning.finance.apitool.service.TestService;
import com.winning.finance.apitool.vo.TestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/24 16:08
 */
@RestController
@Api(tags = "测试接口相关")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value="通过id获取名称",notes = "001")
    @GetMapping("/test")
    public ResponseResult get(@RequestParam("id") Integer id){

        String name = testService.getName(id);

        return ResponseResult.success(name);
    }

    @ApiOperation(value="test1",notes = "002")
    @PostMapping("/test1")
    public String addUser(@Valid @RequestBody TestVo vo)  {
        int i = 1 / 0;
        return "success";
    }
}
