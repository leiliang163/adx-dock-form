/**
 * 文件名： ResultPageUtils.java 此类描述的是： 作者: leiliang 创建时间: 2016年3月23日 上午10:59:14
 */
package com.mjoys.advert.common.utils;


import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.common.wolf.model.Result;
import com.mjoys.common.wolf.model.ResultPage;

import java.util.List;

/**
 * 构造返回结果工具类 .
 *
 * @author: leiliang
 * @version:
 */
public class ResultUtils {

    private ResultUtils() {
    }

    /**
     * 构造一个失败的返回结果.
     *
     * @param <T>       the generic type
     * @param errorCode 错误码
     * @param desc      错误描述
     * @return 失败的返回结果
     */
    public static <T> Result<T> fail(String errorCode, String desc) {
        Result<T> result = new Result<>();
        result.setCode(errorCode);
        result.setDesc(desc);
        return result;
    }

    /**
     * 构造一个失败的返回结果.
     *
     * @param <T>  the generic type
     * @param code 错误码枚举
     * @return the error result do
     */
    public static <T> Result<T> fail(ErrorCode code) {
        Result<T> result = new Result<>();
        result.setCode(code.getErrorCode());
        result.setDesc(code.getDesc());
        return result;
    }

    /**
     * <一句话功能描述>
     *
     * @param t
     * @return
     */
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setCode(ErrorCode.E00000.getErrorCode());
        result.setData(t);
        result.setDesc(ErrorCode.E00000.getDesc());
        return result;
    }

    /**
     * 构造一个成功的返回结果(数据为列表).
     *
     * @param <T>   the generic type
     * @param count 总数
     * @param list  列表数据
     * @return ResultDO 对象(数据为列表)
     */
    public static <T> ResultPage<T> success(long count, List<T> list) {
        ResultPage<T> result = new ResultPage<>();
        result.setCode(ErrorCode.E00000.getErrorCode());
        result.setData(list);
        result.setCount(count);
        return result;
    }
}
