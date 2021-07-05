package com.xc.config;

import com.xc.annotation.Verify;
import com.xc.until.CommonUtils;
import com.xc.until.JsonUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 全局日志
 *
 * @author 肖超
 * @Date 2018/8/29
 */
@Aspect
@Component
public class VerifyAop {

    @Around("@annotation(com.xc.annotation.Verify)||execution(* com.xc..*.*(@com.xc.annotation.Verify (*), ..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (args != null) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            Verify verify = method.getAnnotation(Verify.class);
            Object validator = null;
            if (verify != null) {
                validator = verifyMethod(args);
            } else {
                validator = verifyParam(args, signature);
            }
            if (validator != null) {
                return validator;
            }
        }

        Object ob = pjp.proceed();

        return ob;
    }

    private WebResponse verifyMethod(Object[] args) {
        for (Object arg : args) {
            List<String> validator = CommonUtils.validator(arg);
            if (validator != null && !validator.isEmpty()) {
                return WebResponse.fail("9999", JsonUtil.toJSONString(validator));
            }
        }
        return null;
    }

    private WebResponse verifyParam(Object[] args, MethodSignature signature) {
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        for (Annotation[] annotations : parameterAnnotations) {
            int paramIndex = ArrayUtils.indexOf(parameterAnnotations, annotations);
            for (Annotation annotations2 : annotations) {
                if (annotations2 instanceof Verify) {
                    Object paramValue = args[paramIndex];
                    List<String> validator = CommonUtils.validator(paramValue);
                    if (validator != null && !validator.isEmpty()) {
                        return WebResponse.fail("9999", JsonUtil.toJSONString(validator));
                    }
                }
            }
        }
        return null;
    }
}
