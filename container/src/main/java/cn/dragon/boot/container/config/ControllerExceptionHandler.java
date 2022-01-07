package cn.dragon.boot.container.config;

import cn.dragon.framework.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Configuration
@RestControllerAdvice
public class ControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 参数校验错误 处理
     * @param bindException
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity bindException(Exception bindException) {
        return build(HttpStatus.BAD_REQUEST,String.format("参数校验错误,",((BindException) bindException).getAllErrors().get(0).getDefaultMessage()));
    }

    /**
     * 自定义系统内部异常  用户可参看详情的异常信息处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity ApiExceptionHandler(Exception exception) {
        logger.error(exception.getMessage());
        return build(exception.getMessage());
    }

    /**
     * 其他系统内部异常
     * @param bindException
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity internalExceptionHandler(Exception bindException) {
        String message = "系统内部错误";
        logger.error(message, bindException);
        return build(message);
    }

    private ResponseEntity build(HttpStatus status,String message){
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("error", 1);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(response, httpHeaders, status);
    }

    private ResponseEntity build(String message){
        return build(HttpStatus.INTERNAL_SERVER_ERROR,message);
    }
    


}
