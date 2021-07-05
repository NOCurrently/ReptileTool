package com.xc.config;

import com.xc.po.OperationLog;
import com.xc.service.OperationLogService;
import com.xc.until.JsonUtil;
import com.xc.until.SsoUtil;
import com.xc.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 操作日志
 *
 * @author 肖超
 * @Date 2018/8/29
 */
@Aspect
@Component
public class LogAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);
    @Autowired
    private OperationLogService logService;
    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(20, 100, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1));

    @Around("@annotation(com.xc.annotation.Log)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        try {
            // 异步
            poolExecutor.submit(new PrivateTask(pjp,proceed));
        } catch (Exception e) {
            logger.error("insert log fail ,Exception ", e);
        }
        return proceed;
    }

    class PrivateTask implements Runnable {

        private ProceedingJoinPoint pjp;
        private Object proceed;

        public PrivateTask(ProceedingJoinPoint pjp, Object proceed) {
            this.pjp = pjp;
            this.proceed = proceed;
        }

        @Override
        public void run() {
            try {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                Method method = signature.getMethod();
                Log log = method.getAnnotation(Log.class);

                if (log != null) {
                    OperationLog operationLog = new OperationLog();
                    String className = pjp.getTarget().getClass().getName();
                    String methodName = method.getName();
                    operationLog.setUrl(className + "#" + methodName);
                    operationLog.setCreateBy(SsoUtil.getUserName());
                    Map<String, Object> data = new HashMap<>();
                    data.put("param", pjp.getArgs());
                    data.put("result", proceed);
                    // 数据
                    operationLog.setOperationData(JsonUtil.toJSONString(data));

                    operationLog.setType(log.type());
                    logService.insertSelective(operationLog);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
