package com.luoguohua.finance.framework.handler;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.luoguohua.finance.common.domain.R;
import com.luoguohua.finance.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/10 15:16
 * Content:
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public R<String> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return R.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return R.error(new BusinessException(e.getMessage()));
    }

    /**
     * sql异常
     */
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public R<String> handleServiceException(SQLSyntaxErrorException e, HttpServletRequest request)
    {
        log.error("接口地址：{}",request.getRequestURI(), e);
        log.error("sql异常，原因：{}","系统异常，请稍后重试", e);
        return  R.error(new BusinessException("sql查询异常"));
    }

    /**
     * 第三方接口请求超时异常
     */
    @ExceptionHandler(IORuntimeException.class)
    public R<String> handleSocketTimeoutException(IORuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',第三方接口请求超时异常'{}'", requestURI, e.getMessage());
        return R.error(new BusinessException("第三方接口请求超时，请稍后重试"));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public R<String> handleServiceException(BusinessException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        return R.error(e);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R<String> handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        if(e instanceof BadSqlGrammarException || e.getMessage().contains("KSQLException")){
            log.error("请求地址'{}',发生数据库请求异常.", requestURI, e);
            return R.error(new BusinessException("SQL查询异常"));
        }
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return R.error(new BusinessException("系统内部异常"));
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.error(new BusinessException("系统内部异常"));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public R<String> handleBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.error(new BusinessException(message));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<String> handleBindException(HttpMessageNotReadableException e)
    {
        String message = "系统未知异常，请联系管理员处理";
        log.error(e.getMessage(), e);
        if(e.getCause() instanceof InvalidFormatException){
            message = StrUtil.format("请求参数非法：{}",e.getMessage());
            log.error(e.getMessage(), e);
            return  R.error(new BusinessException(message));
        }
        return R.error(new BusinessException(message));
    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.error(new BusinessException(message));
    }

}
