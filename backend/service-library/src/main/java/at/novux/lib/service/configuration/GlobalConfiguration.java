package at.novux.lib.service.configuration;

import at.novux.lib.service.configuration.interceptor.RestTemplateHeaderInterceptor;
import at.novux.lib.service.configuration.interceptor.WebClientHeaderInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Clock;

@Configuration
public class GlobalConfiguration {

    @Bean
    public WebClient webClientWithoutInterceptor() {
        return WebClient.builder().build();
    }

    @Bean
    public WebClient webClientWithInterceptor(WebClientHeaderInterceptor interceptor) {
        return WebClient.builder()
                .filter(interceptor)
                .build();
    }

    @Bean
    public RestTemplate restTemplateWithoutInterceptors() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateHeaderInterceptor restTemplateHeaderInterceptor) {
        return new RestTemplateBuilder()
                .interceptors(restTemplateHeaderInterceptor)
                .build();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

}
