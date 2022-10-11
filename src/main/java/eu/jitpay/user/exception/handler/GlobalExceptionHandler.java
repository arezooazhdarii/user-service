package eu.jitpay.user.exception.handler;

import eu.jitpay.user.exception.BadRequestException;
import eu.jitpay.user.exception.ForbiddenException;
import eu.jitpay.user.exception.NotFoundException;
import eu.jitpay.user.exception.UserAlreadyExistException;
import eu.jitpay.user.service.dto.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Response<String>> sqlExceptionHandle(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response<String>> constraintViolationException(Exception ex) {
       ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Response<String>> ioExceptionHandler(IOException ex) {
      ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Response<String>> arithmeticExceptionHandler(ArithmeticException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<Response<String>> classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<Response<String>> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Response<String>> nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Response<String>> numberFormatExceptionHandler(NumberFormatException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Messages.INTERNAL_SERVER_ERROR")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(IllegalMonitorStateException.class)
    public ResponseEntity<Response<String>> illegalMonitorStateExceptionHandler(IllegalMonitorStateException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Response<String>> arrayIndexOutOfBoundsExceptionHandler(ArrayIndexOutOfBoundsException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Response<String>> illegalStateExceptionHandler(IllegalStateException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(500)
                .setMessage("Internal Server Error")
                .setError("Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response<String>> userAlreadyExistHandler(UserAlreadyExistException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(400)
                .setMessage(ex.getMessage())
                .setError("Bad Request Error"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response<String>> badRequestHandler(BadRequestException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(400)
                .setMessage(ex.getMessage())
                .setError("Bad Request Error"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<String>> notFoundExceptionHandler(NotFoundException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(404)
                .setMessage(ex.getMessage())
                .setError("Not Found Error"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Response<String>> forbiddenExceptionHandler(ForbiddenException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new Response<String>()
                .setStatus(403)
                .setMessage(ex.getMessage())
                .setError("Forbidden Error"),
                HttpStatus.FORBIDDEN);

    }

}
