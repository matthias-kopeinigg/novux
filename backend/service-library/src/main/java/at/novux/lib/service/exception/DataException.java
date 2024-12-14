package at.novux.lib.service.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

}
