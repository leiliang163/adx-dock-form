package com.mjoys.advert.biz.service;

import com.mjoys.advert.biz.dto.CreativeMarketVerifyDto;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiMaterialDetail;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 16:59.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public interface ICreativeService {
    /**
     * 获取小米所需的素材创意信息
     *
     * @return
     */
    List<XiaoMiMaterialDetail> getMaterialsForXiaoMi();

    /**
     * 查询已提交的创意在小米adx对应的创意ID列表
     *
     * @return
     */
    List<String> getAdxCreativeIds(int market, int verifyStatus);

    /**
     * 批量更新审核状态
     *
     * @param creativeVerifyList
     * @return
     */
    void updateVerifyStatus(List<CreativeMarketVerifyDto> creativeVerifyList);
}
