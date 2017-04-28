package com.mjoys.advert.biz.remoteservice;

import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.advert.common.exception.InnerException;
import com.mjoys.common.wolf.model.DubboResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/14 16:13.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class RemoteBaseService {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public <T> DubboResult<T> exceptionFailure(Exception e) {
        if (e instanceof InnerException) {
            InnerException e1 = (InnerException) e;
            if (logger.isDebugEnabled()) {
                logger.debug("发生内部错误", e);
            }

            return DubboResult.failResult(e1.getResultCode(), e1.getResultMessage());
        } else {
            logger.error("happen unKnow error", e);
            return DubboResult.failResult(ErrorCode.E99999.getErrorCode(), e.getMessage());
        }
    }
}
