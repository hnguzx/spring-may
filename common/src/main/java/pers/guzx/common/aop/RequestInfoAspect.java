package pers.guzx.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pers.guzx.common.util.DateUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/8 11:39
 * @describe 记录Controller请求信息
 */
@Slf4j
@Component
@Aspect
public class RequestInfoAspect {

    @Pointcut("@annotation(pers.guzx.common.annotation.SysLog)")
    public void logPointCut() {
    }

    @Pointcut("execution(public pers.guzx.common.entity.dto.Result pers.guzx..controller..*Controller.*(..))")
    public void requestPointCut() {
    }

    /*@Before("logPointCut()")
    public void beforeAdvice() {
    }*/

    /*@After("logPointCut()")
    public void afterAdvice() {
    }*/

    /*@AfterReturning("logPointCut()")
    public void afterReturnAdvice() {
    }*/


    @Around("requestPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        String dateFormatString = DateUtils.getDateFormatString("yyyy-MM-dd HH:mm:ss");

        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return joinPoint.proceed();
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        try {
            log.info("{}.{},input：{},requestTime：{}", className, methodName, Arrays.asList(args), dateFormatString);
            Object result = joinPoint.proceed();
            Long endTime = System.currentTimeMillis();
            Long executeTime = endTime - startTime;
            log.info("{}.{},requestTime：{},executeTime：{}ms,executeResult：{}", className, methodName, dateFormatString, executeTime,result.toString());
            return result;
        } catch (Throwable e) {
            log.error("{}.{},input：{},requestTime：{},exceptionInfo：{}", className, methodName, Arrays.asList(args), dateFormatString, e.getMessage());
            throw new RuntimeException(e);
        }
    }


    /*@AfterThrowing(value = "logPointCut()", throwing = "exception")
    public void exception(JoinPoint joinPoint, Throwable exception) {
        String dateFormatString = DateUtil.getDateFormatString("yyyy-MM-dd HH:mm:ss");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        log.error("{}.{},参数：{},请求时间：{},异常基本信息：{}", className, methodName, Arrays.asList(args), dateFormatString, exception.getMessage());
    }*/
}
