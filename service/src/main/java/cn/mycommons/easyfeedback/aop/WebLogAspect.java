package cn.mycommons.easyfeedback.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class WebLogAspect {

    @Pointcut("within(cn.mycommons.easyfeedback.controller..*)")
    public void controller() {

    }

    @Around("controller()")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return webLog(true, joinPoint);
    }

    @Pointcut("execution(* cn.mycommons.easyfeedback.repo..*.*(..))")
    public void repo() {

    }

    @Around("repo()")
    public Object repoAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return webLog(false, joinPoint);
    }

    public final Object webLog(boolean logResult, ProceedingJoinPoint joinPoint) throws Throwable {
        long lastTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String method = signature.getDeclaringType().getSimpleName() + "." + signature.getName() + "()";
        String[] names = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        log.info(">>> " + method);

        if (args != null && names != null) {
            for (int i = 0; i < args.length; i++) {
                log.info("args[{}]: {} = {}", (i + 1), names[i], args[i]);
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed(args);
            return result;
        } catch (Throwable e) {
            log.error("{} e = {}", method, e);
            throw e;
        } finally {
            if (logResult) {
                log.info("<<< {} result({}) = {}", method, (System.currentTimeMillis() - lastTime), result);
            } else {
                log.info("<<< {} result({})", method, (System.currentTimeMillis() - lastTime));
            }
        }
    }
}