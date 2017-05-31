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
public class TaskController extends BaseController {

    @Autowired
    private IAdvertiserQIBo advertiserQIBo;

    /**
     * 更新广告主资质审核状态
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "updateAdvertiserQIStatus")
    public Result<Boolean> updateAdvertiserQIStatus() {
        try {
            logger.info("查询广告主审核状态开始");
            advertiserQIBo.updateAdvertiserQIStatus();
            logger.info("查询广告主审核状态结束");
        } catch (Exception e) {
            logger.warn("查询广告主审核状态失败", e);
        }

        return successResult(true);
    }

    /**
     * 新增创意
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "addCreative")
    public Result<Boolean> addCreative() {
        try {
            logger.info("提交创意审核开始");
            advertiserQIBo.addCreative();
            logger.info("提交创意审核结束");
        } catch (Exception e) {
            logger.warn("提交创意审核失败", e);
        }

        return successResult(true);

    }

    /**
     * 更新创意审核状态
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "updateCreativeQIStatus")
    public Result<Boolean> updateCreativeQIStatus() {
        try {
            logger.info("查询创意审核状态开始");
            advertiserQIBo.updateCreativeQIStatus();
            logger.info("查询创意审核状态结束");
        } catch (Exception e) {
            logger.warn("查询创意审核状态失败", e);
        }

        return successResult(true);
    }
}
