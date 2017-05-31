package com.mjoys.advert.biz.third;

import com.mjoys.advert.biz.dto.CreativeMarketVerifyDto;
import com.mjoys.advert.biz.dto.QualVerifyDto;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiAdvertiser;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiAdvertiserQIStatus;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiMaterialDetail;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 17:15.<br/>
 * 功能描述 : 小米资质审核服务.<br/>
 * 变更记录 : .<br/>
 */
public interface IXiaoMiQIService{

    /**
     * 在小米adx平台新增广告主
     * 失败会抛出内部异常
     *
     * @param ads
     */
    QualVerifyDto addAdvertiser(XiaoMiAdvertiser ads);

    /**
     * 在小米adx平台新增广告主
     * 失败会抛出内部异常
     *
     * @param ads
     */
    QualVerifyDto updateAdvertiser(XiaoMiAdvertiser ads);

    /**
     * 批量查询广告主资质审核状态
     * @param advIds
     * @return
     */
    List<XiaoMiAdvertiserQIStatus> queryAdvertiserQIStatus(List<String> advIds);


    /**
     * 批量新增创意
     * @param materials
     * @return
     */
    List<CreativeMarketVerifyDto> addMaterial(List<XiaoMiMaterialDetail> materials);

    /**
     * 批量查询创意审核状态
     * @param materialIds
     * @return
     */
    List<CreativeMarketVerifyDto> queryMaterialQIStatus(List<String> materialIds);

}
