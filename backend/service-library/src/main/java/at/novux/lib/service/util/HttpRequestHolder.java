package at.novux.lib.service.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@RequiredArgsConstructor
@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HttpRequestHolder {

    private final HttpServletRequest httpServletRequest;

    public String getHeaderValue(String key) {
        return httpServletRequest.getHeader(key);
    }

    public String getAuthorizationHeaderValue() {
        return httpServletRequest.getHeader(HeaderConstant.AUTHORIZATION);
    }

    public String getAccessToken() {
        return httpServletRequest.getHeader(HeaderConstant.AUTHORIZATION).replaceFirst("Bearer ", "");
    }

    public String getSubHeaderValue() {
        return httpServletRequest.getHeader(HeaderConstant.SUB);
    }

}
