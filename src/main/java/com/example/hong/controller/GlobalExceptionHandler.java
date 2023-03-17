package com.example.hong.controller;


import com.example.hong.dto.ErrorResponse;
import com.example.hong.error.ErrorCode;
import com.example.hong.error.ValidErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

   /* @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ErrorResponse> handleEmailDuplicateException(RestApiException ex){
        log.error("RestApiException",ex);
        ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }*/

    @ExceptionHandler({IllegalArgumentException.class, NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException2(IllegalArgumentException ex){
        log.error("부적절한 인자값 에러",ex);
        ErrorResponse response = new ErrorResponse(ErrorCode.RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException2(MethodArgumentNotValidException e){
        log.error("파라미터 없음", e);
        ErrorResponse response = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        log.error("handleException",ex);
        ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult) {
        int status = 0;
        String errorCode = "";
        String message = "";

        if(bindingResult.hasErrors()) {

            //Dto에 설정한 message 값을 가져온다.
            message = bindingResult.getFieldError().getDefaultMessage();

            //Dto에 유효성 체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode) {
                case "NotEmpty":
                    errorCode = ValidErrorCode.EMPTY_PARAMETER.getErrorCode();
                    status = ValidErrorCode.EMPTY_PARAMETER.getStatus();
                    break;
                case "Min":
                    errorCode = ValidErrorCode.MIN_VALUE.getErrorCode();
                    status = ValidErrorCode.MIN_VALUE.getStatus();
                    break;
            }
        }

        return new ErrorResponse(status, errorCode, message);
    }
}
