package com.mjoys.advert.biz.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/5/3 15:52.<br/>
 * 功能描述 : 链接工具类.<br/>
 * 变更记录 : .<br/>
 */
public class UrlUtils {

    /**
     * 图片前缀.
     */
    private static final String IMAGE_PREFIX = "http://tr.mjoys.com";

    /**
     * 检查链接域名.<br/>
     * 如果不是以http://tr.mjoys.com或者https://tr.mjoys.com开头，
     * 则前缀加上http://tr.mjoys.com
     *
     * @param srcUrl the src url
     * @return the string
     */
    public static String checkDomain(String srcUrl) {
        if (srcUrl != null) {
            return isHaveDomain(srcUrl) ? srcUrl : IMAGE_PREFIX + srcUrl;
        }
        return null;
    }

    /**
     * 是否有域名.<br/>
     * 如果以http://tr.mjoys.com或者https://tr.mjoys.com开头<br/>
     * 则表示有域名，返回true；反正返回false
     *
     * @param src the src
     * @return the boolean
     */
    private static boolean isHaveDomain(String src) {
        return src.startsWith(IMAGE_PREFIX) || src.startsWith("https://tr.mjoys.com");
    }

    /**
     * string 转成List<String>.<br>
     * 分隔符为","
     *
     * @param arrayStr the array str
     * @return the string list by str
     */
    public static List<String> getUrlsByStr(String arrayStr) {

        if (!StringUtils.isBlank(arrayStr)) {
            List<String> list = new ArrayList<>();
            String[] array = arrayStr.split(",");
            for (String s : array) {
                list.add(checkDomain(s));
            }
            return list;
        } else {
            return Collections.emptyList();
        }

    }
}
