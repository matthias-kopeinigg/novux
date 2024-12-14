package at.novux.lib.service.exception.type;

import at.novux.lib.service.exception.DataException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends DataException {

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, "ENTITY_NOT_FOUND", message);
    }

}
