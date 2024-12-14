package at.novux.lib.service.exception;

import at.novux.lib.service.exception.type.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final GlobalExceptionConverter globalExceptionConverter;

    @ExceptionHandler(DataException.class)
    public ResponseEntity<Map<String, Object>> handleDataException(DataException exception) {
        var errorAttributes = StringUtils.hasLength(exception.getLocalizedMessage()) ?
                globalExceptionConverter.getErrorAttributesWithLocalizedMessage(String.valueOf(exception.getErrorCode()), exception.getLocalizedMessage())
                : globalExceptionConverter.getErrorAttributesWithMessage(String.valueOf(exception.getErrorCode()), exception.getMessage());

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(errorAttributes);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternalException(Exception exception) {
        return createResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException exception) {
        return createResponseEntity(exception, exception.getHttpStatus(), exception.getErrorCode());
    }

    private ResponseEntity<Map<String, Object>> createResponseEntity(Exception exception, HttpStatus httpStatus, String code) {
        return ResponseEntity.status(httpStatus).body(globalExceptionConverter.getErrorAttributesWithMessage(code, exception.getMessage()));
    }

}
