package com.mjoys.advert.deploy.controller;

import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.advert.common.exception.InnerException;
import com.mjoys.advert.common.utils.ResultUtils;
import com.mjoys.common.wolf.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/14 17:08.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class BaseController {

    /**
     * The logger.
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * failResult:(异常处理). <br/>
     *
     * @return
     * @author ZFZ
     * @since JDK 1.6
     */
    @ExceptionHandler
    protected <T> ResponseEntity<Result<T>> handleExceptions(Exception ex) {
        return new ResponseEntity<Result<T>>(buildFailResult(ex), HttpStatus.OK);
    }

    /**
     * 构建失败结果
     * @param e
     * @param <T>
     * @return
     */
    private <T> Result<T> buildFailResult(Exception e) {
        if (e instanceof InnerException) {
            if (logger.isDebugEnabled()) {
                logger.debug("发生内部错误", e);
            }

            InnerException e1 = (InnerException) e;
            return ResultUtils.fail(e1.getResultCode(), e1.getResultMessage());
        } else {
            logger.error("发生系统错误", e);
            return ResultUtils.fail(ErrorCode.E99999);
        }
    }


    /**
     * successResult:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param t
     * @return
     * @author ZFZ
     * @since JDK 1.6
     */
    protected <T> Result<T> successResult(T t) {
        return ResultUtils.success(t);
    }

    /**
     * 参数校验.
     *
     * @param result the result
     */
    protected void checkParam(BindingResult result) {
        if (result.hasErrors()) {
            throw new InnerException(ErrorCode.E01002.getErrorCode(), result.getFieldError().getDefaultMessage());
        }
    }
}
