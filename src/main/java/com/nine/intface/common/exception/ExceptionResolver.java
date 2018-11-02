package com.nine.intface.common.exception;


import com.nine.intface.common.constants.Constant;
import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.HostUnauthorizedException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class ExceptionResolver {

	@Value("${rubi.exception.doLog}")
	private boolean doLog;

	/**
	 * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	 */
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public Result resolveException(Exception ex, HttpServletResponse response) {
		Result result;
		if (ex instanceof SQLException){
			result = ResultFactory.getExceptionInfo(Constant.EXCEPTION_SQLEXCEPTION);
		} else if (	ex instanceof NullParameterException) { //定义过的就用 ex.getMessage
			result = ResultFactory.getExceptionInfo(Constant.EXCEPTION_NULLPARAMETER);
		} else if (ex instanceof NullPointerException){
			result = ResultFactory.getExceptionInfo(Constant.EXCEPTION_NULLPOINTER);
		} else if (ex instanceof NumberFormatException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_NUMBERFORMAT);
		} else if (ex instanceof IndexOutOfBoundsException) {
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_INDEXOUTOFBOUNDS);
		}else if(ex instanceof UnauthenticatedException) {
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_UNAUTHENTICATED);
		}else if(ex instanceof HostUnauthorizedException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_HOSTUNAUTHORIZED);
		}else if (ex instanceof UnauthorizedException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_UNAUTHORIZED);
		}else if (ex instanceof LockedAccountException) {
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_LOCKEDACCOUNT);
		}else if (ex instanceof IncorrectCredentialsException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_INCORRECTCREDENTIALS);
		}else if (ex instanceof UnknownAccountException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_UNKNOWNACCOUNT);
		}else if (ex instanceof ExcessiveAttemptsException) {
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_EXCESSIVEATTEMPTS);
		}else if(ex instanceof ExpiredCredentialsException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_EXPIREDCREDENTIALS);
		}else if(ex instanceof ConcurrentAccessException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_CONCURRENTACCESS);
		}else if(ex instanceof DisabledAccountException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_DISABLEDACCOUNT);
		}else if(ex instanceof UnsupportedTokenException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_UNSUPPORTEDTOKEN);
		}else if(ex instanceof AccountException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_ACCOUNT);
		}else if(ex instanceof CredentialsException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_CREDENTIALS);
		}else if (ex instanceof AuthenticationException) {// //
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_AUTHENTICATION);
		}else if (ex instanceof AuthorizationException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_AUTHORIZATION);
		}else if (ex instanceof MethodArgumentTypeMismatchException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_METHODARGUMENTTYPEMISMATCH);
		}else if (ex instanceof MissingServletRequestParameterException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_MISSINGSERVLETREQUESTPARAMETER);
		} else if (ex instanceof DuplicateKeyException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_DUPLICATEKEYEXCEPTION);
		} else if (ex instanceof DataIntegrityViolationException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_DATAINTEGRITYVIOLATION);
		} else if (ex instanceof BindException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_BIND);
		} else if (ex instanceof BadSqlGrammarException){
			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_BADSQLGRAMMAREXCEPTION);
//		} else if (ex instanceof TemplateEngineException){
//			result =ResultFactory.getExceptionInfo(Constant.EXCEPTION_TEMPLATEENGINEEXCEPTION);

//		} else if (ex instanceof DataIntegrityViolationException){
//		} else if (ex instanceof RuntimeException){
//			result =ResultFactory.getOtherExceptionResult(ex);
		} else {
            result =ResultFactory.getOtherExceptionResult(ex);
		}
		log.warn("异常[{}]",ex.getClass().getName());
		if (doLog){
			log.warn("栈堆信息:",ex);
		}
		return result;
	}
	

}
