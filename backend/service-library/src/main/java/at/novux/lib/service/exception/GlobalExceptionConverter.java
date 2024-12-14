package at.novux.lib.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Component
public class GlobalExceptionConverter extends DefaultErrorAttributes  {

    public Map<String, Object> getErrorAttributesWithMessage(String code, String message) {
        return getErrorAttributesWithLocalizedMessage(code, null, Collections.singletonList(message));
    }

    public Map<String, Object> getErrorAttributesWithLocalizedMessage(String code, String message) {
        return getErrorAttributesWithLocalizedMessage(code, message, Collections.singletonList(message));
    }

    private Map<String, Object> getErrorAttributesWithLocalizedMessage(String code, String message, List<String> details) {
        Map<String, Object> attributes = new LinkedHashMap<>();
        attributes.put("id", UUID.randomUUID().toString());
        attributes.put("timestamp", OffsetDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        attributes.put("severity", "ERROR");
        attributes.put("code", code);
        attributes.put("retryable", false);
        attributes.put("message", message);
        attributes.put("details", details);

        return attributes;
    }

}
