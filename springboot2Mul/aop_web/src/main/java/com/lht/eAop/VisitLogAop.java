package com.lht.eAop;


import com.lht.bService.VisitLogService;
import com.lht.dAnnotation.VisitLog;
import com.lht.dModel.VisitLogModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
public class VisitLogAop {
    @Autowired
    private VisitLogService visitLogService;

    //定义切点
    @Pointcut("@within(com.lht.dAnnotation.VisitLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {

        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
//            logger.info("URL : " + request.getRequestURL().toString());
//            logger.info("HTTP_METHOD : " + request.getMethod());
//            logger.info("IP : " + request.getRemoteAddr());
//            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//            logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
            String className = VisitLogAop.getAnnotationLogForClass(joinPoint);
            String methodName = VisitLogAop.getAnnotationLogForMethod(joinPoint);
//            logger.info(className);
//            logger.info(methodName);
            VisitLogModel model = new VisitLogModel();
            model.setMoudelName(className);
            model.setApiName(methodName);
            model.setIp(request.getRemoteAddr());
            model.setUrl(request.getRequestURL().toString());
            visitLogService.insertVistLog(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("方法的返回值 : " + ret);
//    }
//
//    //后置异常通知
//    @AfterThrowing("logPointCut()")
//    public void throwss(JoinPoint jp) {
//        logger.info("方法异常时执行.....");
//    }
//
//    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
//    @After("logPointCut()")
//    public void after(JoinPoint jp) {
//        logger.info("方法最后执行.....");
//    }
//
//    //环绕通知,环绕增强，相当于MethodInterceptor
//    @Around("logPointCut()")
//    public Object arround(ProceedingJoinPoint pjp) {
//        logger.info("方法环绕start.....");
//        try {
//            Object o = pjp.proceed();
//            logger.info("方法环绕proceed，结果是 :" + o);
//            return o;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    private static String getAnnotationLogForClass(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        Annotation annotation = signature.getDeclaringType().getAnnotation(VisitLog.class);
        String mudoleName = ((VisitLog) annotation).value();
        if ("".endsWith(((VisitLog) annotation).value())) {
            return signature.getDeclaringTypeName();
        } else {
            return mudoleName;
        }
    }

    private static String getAnnotationLogForMethod(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Annotation annotation = method.getAnnotation(VisitLog.class);
        if (annotation != null) {
            String apiName = ((VisitLog) annotation).value();
            if (!"".equals(apiName)) {
                return apiName;
            } else {
                return method.getName();
            }

        } else {
            return method.getName();
        }
    }


}
