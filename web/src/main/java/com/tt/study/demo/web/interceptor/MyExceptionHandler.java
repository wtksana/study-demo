package com.tt.study.demo.web.interceptor;

import com.tt.study.demo.common.MyException;
import com.tt.study.demo.common.RespInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 * date: 2017/12/27
 * author: wt
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public RespInfo exceptionHandler(MyException e) {
        return RespInfo.error("MyException " + e.getMessage());
    }

    @ExceptionHandler()
    @ResponseBody
    public RespInfo exceptionHandler(Exception e) {
        return RespInfo.error(e.getMessage());
    }
}
