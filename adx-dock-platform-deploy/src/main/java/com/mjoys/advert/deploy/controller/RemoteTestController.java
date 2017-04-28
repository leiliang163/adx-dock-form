package com.mjoys.advert.deploy.controller;

import com.mjoys.advert.api.remoteservice.RemoteQIBackendService;
import com.mjoys.common.wolf.model.DubboResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 19:19.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Controller
@RequestMapping("remoteTest")
public class RemoteTestController extends BaseController {
    @Autowired
    private RemoteQIBackendService remoteQIBackendService;

    /**
     * 广告主资质审核接口
     *
     * @param advId 广告主ID
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "addAdvertiserForXiaomi")
    public DubboResult<Boolean> addAdvertiserForXiaomi(@RequestParam Long advId) {
        return remoteQIBackendService.addAdvertiserForXiaoMi(advId);
    }
}
