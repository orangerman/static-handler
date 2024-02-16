package com.javafan.statichandler.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Configuration
public class TraceAopConfig {

    @Pointcut("@annotation(com.javafan.statichandler.annotation.Logs)")
    public void tracePointcut() {}

    /**
     * 自定义日志拦截器
     */
    @Bean
    public Advisor traceAdvisor() {
        CustomerTraceInterceptor
                traceInterceptor = new CustomerTraceInterceptor();
        traceInterceptor.setEnterMessage("Entering className:$[targetClassName]: $[methodName] ($[arguments])");
        traceInterceptor.setExitMessage("Leaving costTime:$[invocationTime], $[methodName]: $[returnValue]");
        traceInterceptor.setUseDynamicLogger(true);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.javafan.statichandler.config.TraceAopConfig.tracePointcut()");
        return new DefaultPointcutAdvisor(pointcut, traceInterceptor);
    }
}
