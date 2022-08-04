package pers.guzx.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pers.guzx.common.util.DateUtil;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/8 11:39
 * @describe 处理SysLog注解
 */
@Slf4j
@Component
@Aspect
public class SysLogAspect {

    /**
     * 切入点
     */
    @Pointcut("@annotation(pers.guzx.common.annotation.SysLog)")
    public void logPointCut() {
    }

//    @Before("logPointCut()")
//    public void beforeAdvice() {
//    }

//    @After("logPointCut()")
//    public void afterAdvice() {
//    }

//    @AfterReturning("logPointCut()")
//    public void afterReturnAdvice() {
//    }

    /**
     * 环绕增强
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        String dateFormatString = DateUtil.getDateFormatString("yyyy-MM-dd HH:mm:ss");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        log.info("{}.{},入参：{},请求时间：{}", className, methodName, Arrays.asList(args), dateFormatString);
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Long executeTime = endTime - startTime;
        log.info("{}.{},执行结果：{},请求时间：{},执行耗时：{}ms", className, methodName, result.toString(), dateFormatString, executeTime);
        return result;
    }

    /**
     * 异常增强
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "logPointCut()", throwing = "exception")
    public void exception(JoinPoint joinPoint, Throwable exception) {
        String dateFormatString = DateUtil.getDateFormatString("yyyy-MM-dd HH:mm:ss");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        log.error("{}.{},参数：{},请求时间：{},异常基本信息：{}", className, methodName, Arrays.asList(args), dateFormatString, exception.getMessage());
    }
}
