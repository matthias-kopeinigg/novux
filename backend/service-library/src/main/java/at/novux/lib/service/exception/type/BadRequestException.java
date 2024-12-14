package at.novux.lib.service.exception.type;

import at.novux.lib.service.exception.DataException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends DataException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, "BAD_REQUEST", message);
    }

}
