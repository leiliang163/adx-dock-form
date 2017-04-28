package com.mjoys.advert.deploy.controller;

import com.mjoys.advert.biz.bo.IAdvertiserQIBo;
import com.mjoys.common.wolf.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/20 9:31.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Controller
@RequestMapping("task")
public class TaskController extends BaseController{

    @Autowired
    private IAdvertiserQIBo advertiserQIBo;

    /**
     * 更新广告主资质审核状态
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "updateAdvertiserQIStatus")
    public Result<Boolean> updateAdvertiserQIStatus() {
        advertiserQIBo.updateAdvertiserQIStatus();
        return successResult(true);
    }

    /**
     * 新增创意
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "addCreative")
    public Result<Boolean> addCreative() {
        advertiserQIBo.addCreative();
        return successResult(true);
    }

    /**
     * 更新创意审核状态
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "updateCreativeQIStatus")
    public Result<Boolean> updateCreativeQIStatus() {
        advertiserQIBo.updateCreativeQIStatus();
        return successResult(true);
    }
}
