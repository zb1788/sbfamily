package com.boz.common.exception;

import com.boz.file.exception.FileSizeLimitExceededException;
import com.boz.utils.JSONResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常捕获
 * @author boz
 * @date 2019/7/25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JSONResult FileSizeLimitException(FileSizeLimitExceededException exception){
        return JSONResult.errorMsg(exception.getMessage());
    }


    @ExceptionHandler
    public Object MissingParameterException( MissingServletRequestParameterException exception){
        return JSONResult.errorMsg("参数" + exception.getParameterName() + "不能为空！");
    }

    @ExceptionHandler
    public Object NohandlerFoundException(NoHandlerFoundException exception){
        return JSONResult.errorMsg("接口地址" + exception.getRequestURL() + "不存在！");
    }


}
