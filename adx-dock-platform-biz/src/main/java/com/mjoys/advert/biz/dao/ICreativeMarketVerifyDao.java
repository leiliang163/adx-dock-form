package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.CreativeMarketVerifyDto;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 10:55.<br/>
 * 功能描述 : 创意和流量市场的对应记录.<br/>
 * 变更记录 : .<br/>
 */
public interface ICreativeMarketVerifyDao {
    /**
     * 根据流量市场和验证类型查询创意审核ID列表
     *
     * @param market 流量市场
     * @param verifyStatus 验证类型
     * @return
     */
    List<CreativeMarketVerifyDto> selectList(Integer market, Integer verifyStatus);

    /**
     * 根据流量市场和验证类型查询adx端的创意ID列表
     *
     * @param market 流量市场
     * @param verifyStatus 验证类型
     * @return
     */
    List<String> selectAdxCreativeIds(Integer market, Integer verifyStatus);

    /**
     * 批量更新审核状态
     *
     * @param creativeVerifyList
     * @return
     */
    int updateVerifyStatus(List<CreativeMarketVerifyDto> creativeVerifyList);
}
