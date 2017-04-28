package com.mjoys.advert.biz.utils.cat;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CatRediscachePlugin {
	
	@Around("execution(* cn.com.duiba.wolf.redis.RedisClient.*(..))")
	public Object redisJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getMethod().getName();
		if(CatInstance.isEnable()){
			Transaction transaction = null;
			if("get".equals(methodName)){
				transaction = Cat.newTransaction("Cache.redis", methodName + ":" + methodName);
			}else{
				transaction = Cat.newTransaction("Cache.redis", methodName);
			}
			try {
				Object cacheValue = joinPoint.proceed();
				if("get".equals(methodName) && cacheValue == null){
					Cat.logEvent("Cache.redis", methodName + ":missed");
				}
				transaction.setStatus(Message.SUCCESS);
				return cacheValue;
			} catch (Throwable e) {
				Cat.logError(e);
				transaction.setStatus(e);
				throw e;
			}finally{
				transaction.complete();
			}
		}else{
			return joinPoint.proceed();
		}
	}
	
}
