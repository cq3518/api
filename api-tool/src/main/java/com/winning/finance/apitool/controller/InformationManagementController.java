package com.winning.finance.apitool.controller;

import com.winning.finance.apitool.base.ResponseResult;
import com.winning.finance.apitool.contant.ApiPathConstant;
import com.winning.finance.apitool.service.api.ApiInformationDetailServiceImpl;
import com.winning.finance.apitool.service.api.ApiInformationDetailUpdateServiceImpl;
import com.winning.finance.apitool.vo.apiinfo.delete.DeleteHangUpInputVO;
import com.winning.finance.apitool.vo.apiinfo.edit.EditHangUpInputVO;
import com.winning.finance.apitool.vo.apiinfo.edit.EditHangUpOutVO;
import com.winning.finance.apitool.vo.apiinfo.hangup.NewHangUpInputVO;
import com.winning.finance.apitool.vo.apiinfo.hangup.NewHangUpOutVO;
import com.winning.finance.apitool.vo.apiinfo.save.SaveApiInfoInputVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/29 10:06
 */
@RestController
@Api(tags = "信息管理")
public class InformationManagementController {



    @Autowired
    private ApiInformationDetailUpdateServiceImpl apiInformationDetailUpdateService;
    @Autowired
    private ApiInformationDetailServiceImpl apiInformationDetailService;

    @ApiOperation(nickname = "新建挂起的API信息", value = "新建挂起的API信息")
    @PostMapping(ApiPathConstant.API_INFORMATION_DETAIL_UPDATE_HANG_UP)
    public ResponseResult apiInformationDetailUpdateHangUp(@Valid @RequestBody NewHangUpInputVO inputVO) {

        NewHangUpOutVO outVO= apiInformationDetailUpdateService.hangUp(inputVO);
        return ResponseResult.success(outVO);
    }


    @ApiOperation(nickname = "修改挂起的API信息", value = "修改挂起的API信息")
    @PostMapping(ApiPathConstant.API_INFORMATION_DETAIL_UPDATE_HANG_UP_EDIT)
    public ResponseResult apiInformationDetailUpdateHangUpEdit(@Valid @RequestBody EditHangUpInputVO inputVO) {

        EditHangUpOutVO outVO= apiInformationDetailUpdateService.editHangUp(inputVO);
        return ResponseResult.success(outVO);
    }

    @ApiOperation(nickname = "撤销挂起的API修改", value = "撤销挂起的API修改")
    @PostMapping(ApiPathConstant.API_INFORMATION_DETAIL_UPDATE_HANG_UP_DELETE)
    public ResponseResult apiInformationDetailUpdateHangUpDelete(@Valid @RequestBody DeleteHangUpInputVO inputVO) {

        apiInformationDetailUpdateService.deleteHangUp(inputVO);
        return ResponseResult.success(null);
    }

    @ApiOperation(nickname = "提交挂起的API修改", value = "提交挂起的API修改")
    @PostMapping(ApiPathConstant.API_INFORMATION_DETAIL_SAVE)
    public ResponseResult apiInformationDetailSave(@Valid @RequestBody SaveApiInfoInputVO inputVO) {

        apiInformationDetailService.save(inputVO);
        return ResponseResult.success(null);
    }

}
