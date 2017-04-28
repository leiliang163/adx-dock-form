package com.mjoys.advert.biz.third.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mjoys.advert.biz.dto.CreativeMarketVerifyDto;
import com.mjoys.advert.biz.dto.QualVerifyDto;
import com.mjoys.advert.biz.model.third.xiaomi.*;
import com.mjoys.advert.biz.service.impl.BaseService;
import com.mjoys.advert.biz.third.IXiaoMiQIService;
import com.mjoys.advert.biz.utils.HttpClientUtils;
import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.advert.common.constants.ThirdUrlEnums;
import com.mjoys.advert.common.exception.InnerException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 18:20.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Service("xiaoMiQIService")
public class XiaoMiQIServiceImpl extends BaseService implements IXiaoMiQIService {

    @Value("${xiaomi.adx.domain}")
    private String xiaoMiDomain;

    @Value("${xiaomi.client.id}")
    private String xiaoClientId;

    @Value("${xiaomi.client.secret}")
    private String xiaoClientSecret;

    @Override
    public QualVerifyDto addAdvertiser(XiaoMiAdvertiser adv) {
        logger.info("addAdvertiser begin, the XiaoMiAdvertiser=[{}]", adv);

        PostMethod postMethod = new PostMethod(buildUrl(ThirdUrlEnums.XIAOMI_ADD_ADVERTISER.getThirdUlr()));
        buildRequestHeader(postMethod);

        List<XiaoMiAdvertiser> advList = new ArrayList<>();
        advList.add(adv);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("advertisers", advList);
        try {
            RequestEntity se = new StringRequestEntity(jsonObject.toJSONString(),
                                                       "application/json", "UTF-8");
            postMethod.setRequestEntity(se);
        } catch (UnsupportedEncodingException e) {
            logger.error("提交广告主审核失败：", e);
            throw new InnerException(ErrorCode.E12005, e);
        }

        JSONObject resultJson = buildMethodResult(postMethod);

        QualVerifyDto addRsp = new QualVerifyDto();
        JSONArray dataJsonArray = resultJson.getJSONArray("result");
        if (isXiaoMiSuccess(resultJson)) {
            // 上传全部成功
            if (dataJsonArray != null && dataJsonArray.size() > 0) {
                String marketAdvId = JSONObject.parseObject(String.valueOf(dataJsonArray.get(0))).getString("advId");
                if (marketAdvId != null) {
                    addRsp.setStatus(QualVerifyDto.STATUS_OF_AUDIT);
                    addRsp.setMarketAdvId(marketAdvId);
                }
            }

        } else {
            logger.info("addAdvertiser error, the resultJson=[{}]", resultJson);

            // 上传失败
            addRsp.setStatus(QualVerifyDto.STATUS_OF_PUSH_FAILED);

            if (dataJsonArray != null && dataJsonArray.size() > 0) {
                // 如果有结果列表的话取出第一个失败原因
                addRsp.setReason(JSONObject.parseObject(String.valueOf(dataJsonArray.get(0))).getString("desc"));

            } else {
                // 如果没有结果列表，则取顶层的失败原因
                addRsp.setReason(resultJson.getString("desc"));
            }
        }

        return addRsp;
    }

    @Override
    public List<XiaoMiAdvertiserQIStatus> queryAdvertiserQIStatus(List<String> advIds) {
        logger.info("queryAdvertiserQIStatus begin, the advIds is={}", advIds);

        GetMethod getMethod = new GetMethod(buildUrl(ThirdUrlEnums.XIAOMI_QUERY_ADVERTISER_QI.getThirdUlr()));
        buildRequestHeader(getMethod);

        NameValuePair[] params = new NameValuePair[advIds.size()];

        for (int i = 0; i < advIds.size(); i++) {
            params[i] = new NameValuePair("advId", advIds.get(i));
        }
        getMethod.setQueryString(params);

        List<XiaoMiAdvertiserQIStatus> qIStatusList = new ArrayList<>(1);
        JSONObject resultJson = buildMethodResult(getMethod);
        if (resultJson == null) {
            return qIStatusList;
        }

        if (isXiaoMiSuccess(resultJson)) {
            // 调用成功
            JSONArray dataJsonArray = resultJson.getJSONArray("result");
            if (dataJsonArray != null && dataJsonArray.size() > 0) {
                qIStatusList = new ArrayList<>(dataJsonArray.size());

                for (Object dataJson : dataJsonArray) {
                    JSONObject jsonObject = JSONObject.parseObject(dataJson.toString());
                    qIStatusList.add(new XiaoMiAdvertiserQIStatus(jsonObject.getString("advId"),
                                                                  jsonObject.getIntValue("status"),
                                                                  jsonObject.getString("rejectReason")));
                }
            }

        } else {
            logger.warn("queryAdvertiserQIStatus error, resultJson={}", resultJson);
            // 调用失败
            throw new InnerException(ErrorCode.E13006.getErrorCode(), resultJson.getString("desc"));
        }

        return qIStatusList;
    }

    @Override
    public List<CreativeMarketVerifyDto> addMaterial(List<XiaoMiMaterialDetail> materials) {
        logger.info("addMaterial begin, the materials={}", materials);

        if (CollectionUtils.isEmpty(materials)) {
            return null;
        }
        int size = materials.size();
        int j = 0;

        List<CreativeMarketVerifyDto> creativeMarketVerifyDtos = new ArrayList<>(size);

        for (int i = 1; i <= size; i++) {

            if (i % BaseXiaoMi.MAX_OF_ADD_CREATIVE_COUNT == 0 || i == size) {
                creativeMarketVerifyDtos.addAll(doAddMaterial(materials.subList(j, i)));
                j = i;
            }
        }

        return creativeMarketVerifyDtos;
    }

    private List<CreativeMarketVerifyDto> doAddMaterial(List<XiaoMiMaterialDetail> materials) {

        PostMethod postMethod = new PostMethod(buildUrl(ThirdUrlEnums.XIAOMI_ADD_CREATIVE.getThirdUlr()));
        buildRequestHeader(postMethod);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("materials", materials);
        try {
            RequestEntity se = new StringRequestEntity(jsonObject.toJSONString(),
                                                       "application/json", "UTF-8");
            postMethod.setRequestEntity(se);
        } catch (UnsupportedEncodingException e) {
            logger.error("推送创意审核失败：", e);
            throw new InnerException(ErrorCode.E12006);
        }

        List<CreativeMarketVerifyDto> addMaterialRsps = new ArrayList<>(1);

        JSONObject resultJson = buildMethodResult(postMethod);
        if (resultJson == null) {
            return addMaterialRsps;
        }
        JSONArray dataJsonArray = resultJson.getJSONArray("result");

        // 取出上传结果描述
        if (dataJsonArray != null && dataJsonArray.size() > 0) {
            addMaterialRsps = new ArrayList<>(dataJsonArray.size());

            for (Object dataJson : dataJsonArray) {
                addMaterialRsps.add(buildCreativeMarketVerify(JSONObject.parseObject(dataJson.toString())));
            }
        }

        return addMaterialRsps;

    }

    /**
     * 是否成功， 状态码为0代表成功
     *
     * @param jsonObject json对象
     * @return true：状态码为0；反正为false
     */
    private boolean isXiaoMiSuccess(JSONObject jsonObject) {
        return BaseXiaoMi.SUCCESS.equals(jsonObject.getString("code"));
    }

    /**
     * 设置请求头
     *
     * @param method http方法
     */
    private void buildRequestHeader(HttpMethod method) {
        method.setRequestHeader("Content-type", "application/json");
        method.setRequestHeader("Authorization", getToken());
    }

    /**
     * 构建推送创意结果对象
     *
     * @param object json对象
     * @return 创意结果对象
     */
    private CreativeMarketVerifyDto buildCreativeMarketVerify(JSONObject object) {
        CreativeMarketVerifyDto addMaterialRsp = new CreativeMarketVerifyDto();
        addMaterialRsp.setRefuseReason(object.getString("desc"));
        addMaterialRsp.setVerifyStatus(isXiaoMiSuccess(object) ? CreativeMarketVerifyDto.VERIFY_OF_PEDING_AUDIT : CreativeMarketVerifyDto.VERIFY_OF_PUSH_FAILED);
        addMaterialRsp.setId(object.getLong("creativeId"));
        addMaterialRsp.setMarketCreativeId(object.getString("materialId"));

        return addMaterialRsp;
    }

    /**
     * 执行批量查询创意审核状态
     *
     * @param materialIds 创意ID列表
     * @return 小米素材审核状态对象列表
     */
    private List<CreativeMarketVerifyDto> doQueryMaterialQIStatus(List<String> materialIds) {
        GetMethod getMethod = new GetMethod(buildUrl(ThirdUrlEnums.XIAOMI_QUERY_CREATIVE_QI.getThirdUlr()));
        buildRequestHeader(getMethod);

        NameValuePair[] params = new NameValuePair[materialIds.size()];

        for (int i = 0; i < materialIds.size(); i++) {
            params[i] = new NameValuePair("advId", materialIds.get(i));
        }
        getMethod.setQueryString(params);

        List<CreativeMarketVerifyDto> qIStatusList = new ArrayList<>(1);
        JSONObject resultJson = buildMethodResult(getMethod);
        if (resultJson == null) {
            return qIStatusList;
        }

        if (isXiaoMiSuccess(resultJson)) {
            // 调用成功
            JSONArray dataJsonArray = resultJson.getJSONArray("result");
            if (dataJsonArray != null && dataJsonArray.size() > 0) {
                qIStatusList = new ArrayList<>(dataJsonArray.size());

                for (Object dataJson : dataJsonArray) {
                    qIStatusList.add(buildCreativeQIStatus(JSONObject.parseObject(dataJson.toString())));
                }
            }

        } else {
            // 调用失败
            logger.warn("queryCreativeQIStatus error, resultJson={}", resultJson);

            throw new InnerException(ErrorCode.E13006.getErrorCode(),
                                     String.valueOf(resultJson.get("desc")));
        }

        return qIStatusList;
    }

    @Override
    public List<CreativeMarketVerifyDto> queryMaterialQIStatus(List<String> materialIds) {
        if (CollectionUtils.isEmpty(materialIds)) {
            return null;
        }
        int size = materialIds.size();
        int j = 0;

        List<CreativeMarketVerifyDto> xiaoMiMaterialQIStatus = new ArrayList<>(size);

        for (int i = 1; i <= size; i++) {

            if (i % BaseXiaoMi.MAX_OF_QUERY_CREATIVE_COUNT == 0 || i == size) {
                xiaoMiMaterialQIStatus.addAll(doQueryMaterialQIStatus(materialIds.subList(j, i)));
                j = i;
            }
        }

        return xiaoMiMaterialQIStatus;
    }

    /**
     * 构建创意审核状态结果对象
     *
     * @param object json对象
     * @return 小米素材审核状态
     */
    private CreativeMarketVerifyDto buildCreativeQIStatus(JSONObject object) {
        CreativeMarketVerifyDto xiaoMiMaterialQIStatus = new CreativeMarketVerifyDto();
        xiaoMiMaterialQIStatus.setMarketCreativeId(object.getString("materialId"));
        xiaoMiMaterialQIStatus.setRefuseReason(object.getString("rejectReason"));
        xiaoMiMaterialQIStatus.setVerifyStatus(object.getIntValue("status"));
        xiaoMiMaterialQIStatus.setId(object.getLongValue("creativeId"));

        return xiaoMiMaterialQIStatus;
    }

    /**
     * 获取小米鉴权token
     *
     * @return 鉴权token
     */
    private String getToken() {

        GetMethod getMethod = new GetMethod(buildUrl(ThirdUrlEnums.XIAOMI_AUTH_TOKEN.getThirdUlr()));
        getMethod.setRequestHeader("Content-type", "application/json");

        NameValuePair[] params = new NameValuePair[2];
        params[0] = new NameValuePair("clientId", xiaoClientId);
        params[1] = new NameValuePair("clientSecret", xiaoClientSecret);
        getMethod.setQueryString(params);

        JSONObject resultJson = buildMethodResult(getMethod);
        if (isXiaoMiSuccess(resultJson)) {
            return resultJson.getString("token");
        }

        logger.warn("getToken error, resultJson={}", resultJson);
        throw new InnerException(ErrorCode.E13002.getErrorCode(),
                                 String.valueOf(resultJson.get("desc")));
    }

    /**
     * 构建请求URL
     *
     * @param srcUrl 接口url
     * @return 调用链接：domain+URL
     */
    private String buildUrl(String srcUrl) {
        return xiaoMiDomain + srcUrl;
    }

    /**
     * 执行远程调用，并把结果转换成JSON格式
     *
     * @param method HttpMethod
     * @return 调用结果json对象
     */
    private JSONObject buildMethodResult(HttpMethod method) {
        return JSONObject.parseObject(HttpClientUtils.doHttpRequest(method));
    }
}
