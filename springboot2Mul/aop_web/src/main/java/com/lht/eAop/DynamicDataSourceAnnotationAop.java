package com.lht.eAop;

import com.lht.register.DynamicDataSourceContextHolder;
import com.lht.dAnnotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Component
@Aspect
public class DynamicDataSourceAnnotationAop {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //定义切点
//    @Pointcut("@within(com.lht.dAnnotation.DataSource)")
    @Pointcut("execution(* com.lht.cDao..*.*(..))")
//    @Pointcut("@target(com.lht.dAnnotation.DataSource)") //错误
    public void dynamicDataSourceCut() {

    }


    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("dynamicDataSourceCut()")
    public Object arround(ProceedingJoinPoint pjp) {
        logger.info("方法环绕start.....");
        try {
            String dynamicDataSourceKey = null;
            String classDskey = DynamicDataSourceAnnotationAop.getAnnotationDataSourceForClass(pjp);
            String methodDskey = DynamicDataSourceAnnotationAop.getAnnotationDataSourceForMethod(pjp);
            if (null != classDskey) {
                dynamicDataSourceKey = classDskey;
            }
            if (null != methodDskey) {
                dynamicDataSourceKey = methodDskey;
            }
            DynamicDataSourceContextHolder.setDataSourceRouterKey(dynamicDataSourceKey);
            Object o = pjp.proceed();
            DynamicDataSourceContextHolder.removeDataSourceRouterKey();
//            logger.info("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            logger.info("清除数据源");
            DynamicDataSourceContextHolder.removeDataSourceRouterKey();
        }
    }


    private static String getAnnotationDataSourceForClass(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        Annotation annotation = signature.getDeclaringType().getAnnotation(DataSource.class);
        if (annotation == null) {
            return null;
        } else {
            return ((DataSource) annotation).value();
        }
    }

    private static String getAnnotationDataSourceForMethod(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Annotation annotation =method.getAnnotation(DataSource.class);
        if (annotation == null) {
            return null;
        } else {
            return ((DataSource) annotation).value();
        }
    }

}
