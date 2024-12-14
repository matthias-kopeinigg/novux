package at.novux.lib.service.configuration.interceptor;

import at.novux.lib.service.util.HeaderConstant;
import at.novux.lib.service.util.HttpRequestHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class RestTemplateHeaderInterceptor implements ClientHttpRequestInterceptor {

    private final HttpRequestHolder httpRequestHolder;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        var httpHeaders = request.getHeaders();
        httpHeaders.add(HeaderConstant.AUTHORIZATION, httpRequestHolder.getAuthorizationHeaderValue());
        httpHeaders.add(HeaderConstant.SUB, httpRequestHolder.getSubHeaderValue());

        return execution.execute(request, body);
    }
}
