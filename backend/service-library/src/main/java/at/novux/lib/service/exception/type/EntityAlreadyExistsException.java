package at.novux.lib.service.exception.type;

import at.novux.lib.service.exception.DataException;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends DataException {

    public EntityAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, "ENTITY_ALREADY_EXISTS", message);
    }

}
