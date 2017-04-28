package com.mjoys.advert.biz.utils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class StringTool.
 */
public class StringTool {

    /**
     * 图片前缀.
     */
    private static final String IMAGE_PREFIX = "http://tr.mjoys.com";

    /**
     * 把一个2,44,51,34,123,123,4（都是数字）形式的字符串解析成一个List<Long>.
     *
     * @param arrayStr the array str
     * @return the long list by str
     */
    public static List<Long> getLongListByStr(String arrayStr) {
        if (StringUtils.isBlank(arrayStr)) {
            return Collections.emptyList();
        }
        String[] array = arrayStr.split(",");
        List<Long> list = new ArrayList<>();
        for (String s : array) {
            list.add(Long.valueOf(s));
        }
        return list;
    }

    /**
     * list转成String.<br>
     * 分隔符为","
     *
     * @param list the list
     * @return the string by list
     */
    public static String getStringByList(List<String> list) {
        if (!CollectionUtils.isEmpty(list)) {
            StringBuilder builder = new StringBuilder();
            for (String str : list) {
                builder.append(str);
                builder.append(",");
            }

            return builder.substring(0, builder.length() - 1);
        }
        return StringUtils.EMPTY;
    }

    /**
     * string 转成List<String>.<br>
     * 分隔符为","
     *
     * @param arrayStr the array str
     * @return the string list by str
     */
    public static List<String> getStringListByStr(String arrayStr) {

        if (!StringUtils.isBlank(arrayStr)) {
            List<String> list = new ArrayList<>();
            String[] array = arrayStr.split(",");
            for (String s : array) {
                list.add(s);
            }
            return list;
        } else {
            return Collections.emptyList();
        }

    }

    /**
     * string 转成List<String>.<br>
     * 分隔符为","
     *
     * @param srcImagList the src imag list
     * @return the string list by str
     */
    public static List<String> getImageListByStr(String srcImagList) {

        if (!StringUtils.isBlank(srcImagList)) {
            List<String> list = new ArrayList<>();
            String[] array = srcImagList.split(",");
            for (String s : array) {

                list.add(isHaveDomain(s) ? s : IMAGE_PREFIX + s);
            }
            return list;
        } else {
            return Collections.emptyList();
        }
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
}
