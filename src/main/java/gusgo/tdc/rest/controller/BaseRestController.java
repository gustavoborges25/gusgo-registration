package gusgo.tdc.rest.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import gusgo.tdc.rest.exception.BusinessException;
import gusgo.tdc.rest.exception.RestException;
import gusgo.tdc.rest.resource.ErrorResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseRestController {

    protected static final Logger logger = Logger.getLogger(BaseRestController.class.getName());

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleException(RestException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleException(BusinessException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> erros = bindingResult.getFieldErrors();
        String campo = erros.get(0).getField();
        String mensagem = String.format("%s: %s", campo, erros.get(0).getDefaultMessage());

        logger.log(Level.SEVERE, mensagem, exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(mensagem));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException exception) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Request body is missing or with error";

        Throwable root = exception.getRootCause();
        if (root != null) {
            if (root instanceof InvalidFormatException invalidFormatException) {
                message = String.format("Value '%s' is not a valid for '%s'.",
                        invalidFormatException.getValue(),
                        invalidFormatException.getPath().get(0).getFieldName());
            } else {
                message = root.getMessage(); // Captura a mensagem genérica da exceção
            }
        }

        logger.log(Level.SEVERE, message, exception);

        return ResponseEntity.status(status).body(new ErrorResponse(message));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleException(DateTimeParseException exception) {

        logger.log(Level.SEVERE, exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name()));
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ChangeSetPersister.NotFoundException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponse> handleException(MissingRequestHeaderException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleException(MissingServletRequestParameterException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("The provided value was not of the correct type: " + exception.getCause().getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    }

}
