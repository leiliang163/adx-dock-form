package com.mjoys.advert.biz.model.third.xiaomi;

import com.alibaba.dubbo.common.json.JSON;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 17:25.<br/>
 * 功能描述 : 小米创意对象.<br/>
 * 变更记录 : .<br/>
 */
public class XiaoMiMaterialDetail implements Serializable {

    private static final long serialVersionUID = 1468732344070278793L;

    /**
     * DSP 侧创意 ID.
     **/
    private String creativeId;

    /**
     * 小米侧广告主 ID.
     **/
    private String advId;

    /**
     * 模板 ID.
     **/
    private String templateId;

    /**
     * 标题.
     **/
    private String title;

    /**
     * 广告来源.
     **/
    private String source;

    /**
     * 图片地址.
     **/
    private List<String> imgUrl;

    /**
     * 目标地址.
     **/
    private String landingUrl;

    /**
     * 下载链接.
     **/
    private String actionUrl;

    /**
     * 视频地址.
     **/
    private String videoUrl;

    /**
     * 下载应用包名.
     **/
    private String packageName;

    /**
     * 贴片时长.
     **/
    private int duration;

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLandingUrl() {
        return landingUrl;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
