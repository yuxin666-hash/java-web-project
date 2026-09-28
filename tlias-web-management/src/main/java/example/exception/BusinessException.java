package example.exception;

/**
 *  业务异常，用于抛出需要提示给用户的业务规则错误信息
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
