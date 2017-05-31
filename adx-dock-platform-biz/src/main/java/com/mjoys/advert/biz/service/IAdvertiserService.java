package com.mjoys.advert.biz.service;

import com.mjoys.advert.biz.dto.QualVerifyDto;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiAdvertiser;

import java.util.List;
import java.util.Map;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 17:33.<br/>
 * 功能描述 : 广告主资质审核服务.<br/>
 * 变更记录 : .<br/>
 */
public interface IAdvertiserService {

    /**
     * 获取小米所需的广告主信息
     *
     * @param advId the adv id
     *
     * @return advertiser detail for xiao mi
     */
    XiaoMiAdvertiser getAdvertiserDetailForXiaoMi(Long advId);

    /**
     * 根据审核状态和adx类型查询对应adx端的广告主ID列表
     *
     * @param status the status
     * @param market the market
     *
     * @return list list
     */
    List<String> selectMarketAdvIds(Integer status, Integer market);

    /**
     * 是否处于审核中或者审核通过状态
     *
     * @param advId  the adv id
     * @param market the market
     *
     * @return boolean boolean
     */
    boolean isSuccessOrAuditOfStatus(Long advId, int market);

    /**
     * 批量审核通过
     *
     * @param marketAdvIds 流量市场广告主ID列表
     *
     * @return int int
     */
    int updatePassList(List<String> marketAdvIds);

    /**
     * 批量审核拒绝
     * <p>
     * 参数说明：
     * marketAdvId：adx端广告主ID<br/>
     * reason：拒绝原因<br/>
     *
     * @param params the params
     *
     * @return int int
     */
    int updateRejectList(List<Map<String, Object>> params);


    /**
     * 新增广告主审核记录
     *
     * @param qualVerifyDto the qual verify dto
     *
     * @return int int
     */
    int insertQIRecord(QualVerifyDto qualVerifyDto);

    /**
     * 修改审核状态.
     * <p>
     * 必传参数说明：
     * marketAdvId：adx端广告主ID<br/>
     * status：状态<br/>
     *
     * @param marketAdvId the market adv id
     * @param status      the status
     * @param reason      the reason
     *
     * @return int int
     */
    boolean updateByMarketId(String marketAdvId, int status, String reason);

}
