package com.mjoys.advert.biz.service.impl;

import cn.oasistech.pbinfo.Enums;
import com.mjoys.advert.biz.dao.ICreativeMarketVerifyDao;
import com.mjoys.advert.biz.dao.IQualVerifyDao;
import com.mjoys.advert.biz.dao.ITeamCNTemFieldDao;
import com.mjoys.advert.biz.dao.ITeamCreativeVerifyDao;
import com.mjoys.advert.biz.dto.*;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiMaterialDetail;
import com.mjoys.advert.biz.service.ICreativeService;
import com.mjoys.advert.biz.utils.StringTool;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * The type Creative service.
 */
@Repository("creativeService")
public class CreativeServiceImpl extends BaseService implements ICreativeService {

    /**
     * 小米固定的素材来源ID.
     */
    private static final String      DEFAULT_CREATIVE_SOURCE_FOR_XIAOMI = "摸象大数据";


    @Autowired
    private ICreativeMarketVerifyDao creativeMarketVerifyDao;

    @Autowired
    private ITeamCreativeVerifyDao   teamCreativeVerifyDao;

    @Autowired
    private ITeamCNTemFieldDao       teamCNTemFieldDao;

    @Autowired
    private IQualVerifyDao           qualVerifyDao;

    @Override
    public List<XiaoMiMaterialDetail> getMaterialsForXiaoMi() {
        // 1.查询推送中的创意列表
        List<CreativeMarketVerifyDto> verifyList = creativeMarketVerifyDao.selectList(Enums.Market.XIAOMI_VALUE,
                                                                                      CreativeMarketVerifyDto.VERIFY_OF_PUSH_IN);
        if (CollectionUtils.isEmpty(verifyList)) {
            return ListUtils.EMPTY_LIST;
        }

        // 2.临时转换所需参数
        // 审核创意ID。用于后续查询创意广告主、模板等信息
        List<Long> verifyIds = new ArrayList<>(verifyList.size());
        // map<审核创意ID,审核ID>
        Map<Long, Long> creativeVerifyMap = new HashMap<>(verifyList.size());
        for (CreativeMarketVerifyDto verify : verifyList) {
            verifyIds.add(verify.getVerifyId());
            creativeVerifyMap.put(verify.getVerifyId(), verify.getId());
        }

        // 3.批量查询创意信息(广告主ID，模板ID，创意ID等)
        List<TeamCreativeVerifyDto> creativeVerifyList = teamCreativeVerifyDao.selectByIds(verifyIds);
        if (CollectionUtils.isEmpty(creativeVerifyList)) {
            return ListUtils.EMPTY_LIST;
        }

        // 4.查询小米侧广告主ID
        Set<Long> advIds = new HashSet<>();
        for (TeamCreativeVerifyDto creativeVerify : creativeVerifyList) {
            advIds.add(creativeVerify.getAdvId());
        }

        // 5.Map<dsp广告主ID,流量市场广告主ID>
        Map<Long, String> advIdMap = buildMarketAdvIdMap(new ArrayList<>(advIds));

        // 6.构建查询结果
        return buildMaterialsForXiaoMiRsp(creativeVerifyList, creativeVerifyMap, advIdMap);
    }

    /**
     * 构建查询创意结果.
     *
     * @param creativeVerifyList the creative verify list
     * @param creativeVerifyMap  the creative verify map
     * @param advIdMap           the adv id map
     *
     * @return the list
     */
    private List<XiaoMiMaterialDetail> buildMaterialsForXiaoMiRsp(List<TeamCreativeVerifyDto> creativeVerifyList,
                                                                  Map<Long, Long> creativeVerifyMap,
                                                                  Map<Long, String> advIdMap) {

        List<XiaoMiMaterialDetail> xiaoMiMaterials = new ArrayList<>(creativeVerifyList.size());

        // 3.获取创意ID列表
        List<Long> creativeIds = new ArrayList<>(creativeVerifyList.size());

        for (TeamCreativeVerifyDto creativeVerify : creativeVerifyList) {
            Long creativeId = creativeVerify.getCreativeId();
            creativeIds.add(creativeId);

            Long verifyId = creativeVerifyMap.get(creativeVerify.getId());
            // map<创意ID,审核ID>
            creativeVerifyMap.put(creativeId, verifyId);

            XiaoMiMaterialDetail xiaoMiMaterial = new XiaoMiMaterialDetail();
            // 流量市场广告主ID
            xiaoMiMaterial.setAdvId(advIdMap.get(creativeVerify.getAdvId()));
            // 创意审核ID(t_creative_market_verify.id)
            xiaoMiMaterial.setCreativeId(String.valueOf(verifyId));
            // 流量市场模板ID
            xiaoMiMaterial.setTemplateId(creativeVerify.getMatchTemplateId());

            xiaoMiMaterials.add(xiaoMiMaterial);
        }

        // 4.批量查询创意字段信息
        Map<Long, Map<String, Object>> failedMaps = buildFailedMaps(creativeVerifyMap,
                                                                    creativeVerifyList,
                                                                    creativeIds);

        // 5.塞入字段信息
        buildXiaoMiMaterials(xiaoMiMaterials, failedMaps);

        return xiaoMiMaterials;
    }

    /**
     * 构建Map<dsp端广告主ID,流量市场广告主ID>.
     *
     * @param advIds dsp端广告主ID列表
     * @return the map
     */
    private Map<Long, String> buildMarketAdvIdMap(List<Long> advIds) {
        List<QualVerifyDto> verifyDtoList = qualVerifyDao.selectListByMarket(advIds,
                                                                             Enums.Market.XIAOMI_VALUE);
        Map<Long, String> advIdMap = new HashMap<>(verifyDtoList.size());
        for (QualVerifyDto verifyDto : verifyDtoList) {
            advIdMap.put(verifyDto.getAdvId(), verifyDto.getMarketAdvId());
        }

        return advIdMap;
    }

    /**
     * Build failed maps map.
     *
     * @param creativeVerifyMap  the creative verify map
     * @param creativeVerifyList the creative verify list
     * @param creativeIds        the creative ids
     *
     * @return the map
     */
    private Map<Long, Map<String, Object>> buildFailedMaps(Map<Long, Long> creativeVerifyMap,
                                                           List<TeamCreativeVerifyDto> creativeVerifyList,
                                                           List<Long> creativeIds) {

        // 1.批量查询创意的字段列表
        List<TeamCNTemFieldDto> fields = teamCNTemFieldDao.selectByCreativeIds(creativeIds);

        // 2.构造返回结果：Map<创意ID, Map<创意字段, 创意字段值>>
        Map<Long, Map<String, Object>> failedMaps = new HashMap<>(creativeVerifyList.size());
        for (TeamCreativeVerifyDto creativeVerify : creativeVerifyList) {
            Long creativeId = creativeVerify.getCreativeId();

            Map<String, Object> failedMap = new HashMap<>();
            failedMaps.put(creativeVerifyMap.get(creativeId), failedMap);

            for (TeamCNTemFieldDto field : fields) {
                if (creativeId.longValue() == field.getCreativeId()) {
                    failedMap.put(field.getFieldName(), field.getFieldValue());
                }
            }
        }

        return failedMaps;
    }

    /**
     * Build xiao mi materials.
     *
     * @param xiaoMiMaterials the xiao mi materials
     * @param failedMaps the failed maps
     */
    private void buildXiaoMiMaterials(List<XiaoMiMaterialDetail> xiaoMiMaterials,
                                      Map<Long, Map<String, Object>> failedMaps) {
        for (XiaoMiMaterialDetail xiaoMiMaterial : xiaoMiMaterials) {
            String creativeId = xiaoMiMaterial.getCreativeId();
            Map<String, Object> failedMap = failedMaps.get(Long.valueOf(creativeId));
            if (failedMap == null) {
                continue;
            }
            Object title = failedMap.get("title");
            if (title != null) {
                xiaoMiMaterial.setTitle(String.valueOf(title));
            }
            xiaoMiMaterial.setSource(DEFAULT_CREATIVE_SOURCE_FOR_XIAOMI);
            Object imgUrl = failedMap.get("imgurl");
            if (imgUrl != null) {
                xiaoMiMaterial.setImgUrl(StringTool.getImageListByStr(String.valueOf(imgUrl)));
            }
            Object landingUrl = failedMap.get("landingurl");
            if (landingUrl != null) {
                xiaoMiMaterial.setLandingUrl(String.valueOf(landingUrl));
            }
            Object actionUrl = failedMap.get("actionurl");
            if (actionUrl != null) {
                xiaoMiMaterial.setActionUrl(String.valueOf(actionUrl));
            }
            Object videoUrl = failedMap.get("videourl");
            if (videoUrl != null) {
                xiaoMiMaterial.setVideoUrl(String.valueOf(videoUrl));
            }
            Object packageName = failedMap.get("packagename");
            if (packageName != null) {
                xiaoMiMaterial.setPackageName(String.valueOf(packageName));
            }
            Object strDuration = failedMap.get("duration");
            if (strDuration != null) {
                try {
                    xiaoMiMaterial.setDuration(Integer.valueOf(String.valueOf(strDuration)));
                } catch (NumberFormatException e) {

                }

            }
        }
    }

    @Override
    public void updateVerifyStatus(List<CreativeMarketVerifyDto> creativeVerifyList) {
        if (CollectionUtils.isEmpty(creativeVerifyList)) {
            return;
        }

        creativeMarketVerifyDao.updateVerifyStatus(creativeVerifyList);
    }

    @Override
    public List<String> getAdxCreativeIds(int market, int verifyStatus) {
        return creativeMarketVerifyDao.selectAdxCreativeIds(market, verifyStatus);
    }
}
