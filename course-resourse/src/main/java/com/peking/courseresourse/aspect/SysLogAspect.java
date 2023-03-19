package com.peking.courseresourse.aspect;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import annotation.SysLog;
import com.peking.courseresourse.entity.SysLogEntity;
import com.peking.courseresourse.service.SysLogService;
import dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.IpHelper;
import utils.UserHolder;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint joinPoint, SysLog sysLog) throws Throwable {
        long id = Thread.currentThread().getId();
        log.info("aop线程:{}",id);
        long beginTime = SystemClock.now();
        //执行方法
        Object result = joinPoint.proceed();
        //执行时长(毫秒)
        long time = SystemClock.now() - beginTime;


        SysLogEntity sysLogEntity = new SysLogEntity();
        if(sysLog != null){
            //注解上的描述
            sysLogEntity.setOperation(sysLog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        sysLogEntity.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        log.info("请求的参数"+ Arrays.toString(args));
        String params = JSON.toJSONString(args[0]);
        sysLogEntity.setParams(params);

        //设置IP地址
        sysLogEntity.setIp(IpHelper.getIpAddr());

        //用户名
        UserDTO user = UserHolder.getUser();
        String username = null;
        if(user != null){
          username  = user.getUsername();
        }
        sysLogEntity.setUsername(username);

        sysLogEntity.setTime(time);
        sysLogEntity.setCreateDate(new Date());
        //保存系统日志
        sysLogService.save(sysLogEntity);
        log.info("保存的系统日志:{}",sysLogEntity);

        return result;
    }
}
