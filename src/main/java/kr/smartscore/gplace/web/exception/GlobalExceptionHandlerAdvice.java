package kr.smartscore.gplace.web.exception;

import javassist.NotFoundException;
import kr.smartscore.gplace.web.ResultInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@ControllerAdvice
@SuppressWarnings({"unchecked", "rawtypes"})
public class GlobalExceptionHandlerAdvice {
    @ExceptionHandler({Exception.class, SQLException.class})
    public ResponseEntity unknownExceptionHandler(Exception ex) {
        //ex.printStackTrace();
        ResultInfo rs = new ResultInfo();
        return new ResponseEntity(rs, HttpStatus.OK);
    }

    @ExceptionHandler({ResponseException.class})
    public ResponseEntity responseExceptionHandler(Exception ex) {
        //ex.printStackTrace();
        ResultInfo rs = new ResultInfo();
        return new ResponseEntity(rs, HttpStatus.OK);
    }



    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public void httpMediaTypeNotAcceptableException(Exception ex) {
        ex.printStackTrace();
    }
}
/*
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity handleGenericNotFoundException(NotFoundException e) {
        ResultInfo rs = new ResultInfo();
        return new ResponseEntity(rs, HttpStatus.OK);
    }
}
 */
