package example.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import example.pojo.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理业务异常
    @ExceptionHandler(BusinessException.class)
    public Result businessException(BusinessException e) {
        log.warn("业务异常: {}", e.getMessage()); // 业务异常属于预期内的，记录为警告即可
        // 把具体的业务提示信息返回给前端
        return Result.error(e.getMessage());
    }

    // 处理异常
    @ExceptionHandler
    public Result ex(Exception e) { // 方法形参中指定能够处理的异常类型
        log.error("程序运行异常", e); // 记录异常信息
        // 捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }

}
