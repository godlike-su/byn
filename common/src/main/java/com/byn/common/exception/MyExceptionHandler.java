package com.byn.common.exception;//package com.exception;

import com.byn.common.exception.result.ErrorResult;
import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class MyExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);


    @ExceptionHandler(value = GenerallyeException.class)
    @ResponseBody
    public ErrorResult generallyeExceptionHandler(GenerallyeException e) {
        logger.error(e.getResultMsg());
        return new ErrorResult(e.getResultMsg());
    }

    @ExceptionHandler(value = ParamerException.class)
    @ResponseBody
    public ErrorResult paramExceptionHandler(ParamerException e) {
        logger.error(e.getResultMsg());
        return new ErrorResult(e.getResultMsg());
    }

    @ExceptionHandler(value = DataNullException.class)
    @ResponseBody
    public ErrorResult dataNullExceptionExceptionHandler(DataNullException e) {
        logger.error(e.getResultMsg());
        return new ErrorResult(e.getResultMsg());
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public ErrorResult handleValidationException(ValidationException e) {
        logger.error(e.getMessage(), e);
        return new ErrorResult(e.getCause().getMessage());
    }

    /**
     * MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < allErrors.size(); i++) {
            ObjectError objectError = allErrors.get(i);
            String[] strings = Optional.ofNullable(objectError).map(ObjectError::getCodes).orElse(null);
            String errorMessage = Optional.ofNullable(strings).map(s -> s[0]).orElse("");
            int lastIndexOf = errorMessage.lastIndexOf(".");
            if (lastIndexOf != -1) {
                String substring = errorMessage.substring(lastIndexOf + 1, errorMessage.length());
                stringBuffer.append(substring).append(" ");
            }
        }
        String message = allErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.joining(";"));
        stringBuffer.append(message);
        logger.error(stringBuffer.toString());
        return new ErrorResult(stringBuffer.toString());
    }

    /**
     * openfeign获取下游异常，并截取字符处理返回
     *
     * @param e
     * @return
     */
    @ExceptionHandler(FeignException.class)
    public ErrorResult feignExceptionHandler(FeignException e) {
        logger.error(e.getMessage());
        String[] status = StringUtils.substringsBetween(e.getLocalizedMessage(), "\"status\":\"", "\",");
        String[] message = StringUtils.substringsBetween(e.getLocalizedMessage(), "\"message\":\"", "\"}]");
        if (!ObjectUtils.isEmpty(status) && !ObjectUtils.isEmpty(message)) {
            return new ErrorResult(status[0], message[0]);
        }
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorResult exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return new ErrorResult("内部错误!");
    }
}
