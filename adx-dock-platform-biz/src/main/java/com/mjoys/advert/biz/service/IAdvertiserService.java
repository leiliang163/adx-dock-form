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
     * @param advId
     * @return
     */
    XiaoMiAdvertiser getAdvertiserDetailForXiaoMi(Long advId);

    /**
     * 根据审核状态和adx类型查询对应adx端的广告主ID列表
     *
     * @param status
     * @param market
     * @return
     */
    List<String> selectMarketAdvIds(Integer status, Integer market);

    /**
     * 是否处于审核中或者审核通过状态
     *
     * @param advId
     * @param market
     * @return
     */
    boolean isSuccessOrAuditOfstatus(Long advId, int market);

    /**
     * 批量审核通过
     *
     * @param marketAdvIds 流量市场广告主ID列表
     * @return
     */
    int updatePassList(List<String> marketAdvIds);

    /**
     * 批量审核拒绝
     * <p>
     * 参数说明：
     * marketAdvId：adx端广告主ID<br/>
     * reason：拒绝原因<br/>
     *
     * @param params
     * @return
     */
    int updateRejectList(List<Map<String, Object>> params);


    /**
     * 新增广告主审核记录
     *
     * @param qualVerifyDto
     * @return
     */
    int insertQIRecord(QualVerifyDto qualVerifyDto);

}
