package com.mjoys.advert.deploy.task;

import com.mjoys.advert.biz.bo.IAdvertiserQIBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/21 11:44.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
//@Component
public class IAdveQITask implements InitializingBean {

    private static final Timer  TIMER  = new Timer("IAdveQITask");

    private static final Logger LOGGER = LoggerFactory.getLogger(IAdveQITask.class);

    @Autowired
    private IAdvertiserQIBo     advertiserQIBo;

    @Override
    public void afterPropertiesSet() throws Exception {

        // 10分钟后执行， 频率：6小时
        long delay = 1000 * 10l;

        TIMER.scheduleAtFixedRate(exectueAddCreative(), delay, TimeUnit.HOURS.toMillis(1));

        TIMER.scheduleAtFixedRate(exectueUpdateCtQIStatus(), delay, TimeUnit.HOURS.toMillis(1));

        TIMER.scheduleAtFixedRate(exectueUpdateAdvQIStatus(), delay, TimeUnit.HOURS.toMillis(1));
    }

    /**
     * 推送创意审核定时任务
     *
     * @return
     */
    public TimerTask exectueAddCreative() {
        return new TimerTask() {

            @Override
            public void run() {
                try {
                    advertiserQIBo.addCreative();
                } catch (Exception e) {
                    LOGGER.error("execture addCreative task error, because of:", e);
                }

            }
        };
    }

    /**
     * 更新创意审核状态定时任务
     *
     * @return
     */
    public TimerTask exectueUpdateCtQIStatus() {
        return new TimerTask() {

            @Override
            public void run() {
                try {
                    advertiserQIBo.updateCreativeQIStatus();
                } catch (Exception e) {
                    LOGGER.error("execture updateCreativeQIStatus task error, because of:", e);
                }

            }
        };
    }

    /**
     * 更新广告主审核状态定时任务
     *
     * @return
     */
    public TimerTask exectueUpdateAdvQIStatus() {
        return new TimerTask() {

            @Override
            public void run() {
                try {
                    advertiserQIBo.updateAdvertiserQIStatus();
                } catch (Exception e) {
                    LOGGER.error("execture updateAdvertiserQIStatus task error, because of:", e);
                }

            }
        };
    }
}
