package at.novux.lib.service.util;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public final class StreamUtils {

    public static <T> Stream<T> nullSafeStream(Collection<T> collection) {
        return Optional.ofNullable(collection).stream().flatMap(Collection::stream);
    }

}
