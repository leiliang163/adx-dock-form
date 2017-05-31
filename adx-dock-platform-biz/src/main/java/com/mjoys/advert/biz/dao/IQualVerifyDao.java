package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.QualVerifyDto;
import com.mjoys.advert.biz.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 14:20.<br/>
 * 功能描述 : 广告主审核记录表.<br/>
 * 变更记录 : .<br/>
 */
public interface IQualVerifyDao {

    /**
     * 新增广告主审核记录
     *
     * @param qualVerifyDto the qual verify dto
     *
     * @return int int
     */
    int insert(QualVerifyDto qualVerifyDto);

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
     * 根据审核状态和adx类型查询对应adx端的广告主ID列表
     *
     * @param status the status
     * @param market the market
     *
     * @return list list
     */
    List<String> selectMarketAdvIds(Integer status, Integer market);

    /**
     * 查询列表
     *
     * @param advId  dsp端广告主ID
     * @param market 流量市场类型
     * @param status 审核状态列表
     *
     * @return list list
     */
    List<QualVerifyDto> selectListByStatus(Long advId, int market, List<Integer> status);

    /**
     * 查询列表
     *
     * @param advIds the adv ids
     * @param market 流量市场类型
     *
     * @return list list
     */
    List<QualVerifyDto> selectListByMarket(List<Long> advIds, int market);

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
    int updateByMarketId(String marketAdvId, int status, String reason);
}
