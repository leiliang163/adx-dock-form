/**
 * 文件名： BaseJunit4Test.java 此类描述的是： 作者: leiliang 创建时间: 2016年3月30日 上午10:11:39
 */
package com.mjoys.advert.base;

import com.mjoys.advert.deploy.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author: leiliang
 * @version:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(properties = "spring.config.location=classpath:adx-dock-platform.properties")
@ActiveProfiles("noRegister") // noRegister
                              // 不注册dubbo服务，单元测试时不需要，开发时也可以在启动参数加入-Dspring.profiles.active=noRegister加快启动速度
@Transactional
public class BaseJunit4Test {

    @Test
    public void testBase() {
        // 1.操作：增加广告主账户资金记录 预期：增加成功，创建时间为insert时间
        Assert.assertTrue(true);
    }
}
