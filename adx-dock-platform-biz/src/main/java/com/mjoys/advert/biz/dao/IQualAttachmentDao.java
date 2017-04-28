package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.QualAttachmentDto;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 14:32.<br/>
 * 功能描述 : 资质附件表.<br/>
 * 变更记录 : .<br/>
 */
public interface IQualAttachmentDao {

    /**
     * 查询广告主的资质附件列表
     * @param advId
     * @return
     */
    List<QualAttachmentDto> selectListByAdvId(Long advId);
}
