package at.novux.lib.service.configuration.interceptor;

import at.novux.lib.service.util.HeaderConstant;
import at.novux.lib.service.util.HttpRequestHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WebClientHeaderInterceptor implements ExchangeFilterFunction {

    private final HttpRequestHolder httpRequestHolder;

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        MultiValueMap<String, String> httpHeaders = new LinkedMultiValueMap<>();
        httpHeaders.add(HeaderConstant.AUTHORIZATION, httpRequestHolder.getAuthorizationHeaderValue());
        httpHeaders.add(HeaderConstant.SUB, httpRequestHolder.getSubHeaderValue());

        var requestWithHeaders = ClientRequest.from(request)
                .headers(httpHeaders1 -> httpHeaders1.addAll(new HttpHeaders(httpHeaders)))
                .build();


        return next.exchange(requestWithHeaders);
    }
}
