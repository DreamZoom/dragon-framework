package cn.dragon.framework.exception;

/**
 * api 调用异常
 */
public class ApiException extends InnerException {
    public ApiException(String message) {
        super(message);
    }
}
