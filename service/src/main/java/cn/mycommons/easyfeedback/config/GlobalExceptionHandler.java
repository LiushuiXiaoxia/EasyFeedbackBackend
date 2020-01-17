package cn.mycommons.easyfeedback.config;

import cn.mycommons.easyfeedback.dto.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handler(HttpServletRequest request, Exception e) {
        log.error("{} -> {} fail", request.getMethod(), request.getRequestURI(), e);
        CommonResp<Object> error = CommonResp.serverError(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}