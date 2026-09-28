package example.operation;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.mapper.OperateLogMapper;
import example.pojo.OperateLog;
import example.util.CurrentUserHolder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕通知：只拦截标注了 @LogOperation 的方法
    @Around("@annotation(example.operation.LogOperation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 耗时
        long costTime = System.currentTimeMillis() - startTime;

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result == null ? null : result.toString());
        operateLog.setCostTime(costTime);

        // 插入日志：记录日志失败不能影响正常业务
        try {
            operateLogMapper.insert(operateLog);
        } catch (Exception e) {
            log.error("保存操作日志失败", e);
        }
        return result;
    }

    /**
     * 获取当前登录用户ID，未登录时返回null
     */
    private Integer getCurrentUserId() {
        return CurrentUserHolder.get();
    }
}
