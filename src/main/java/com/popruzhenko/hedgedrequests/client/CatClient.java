package com.popruzhenko.hedgedrequests.client;

import com.popruzhenko.hedgedrequests.dto.CatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CatClient {

    private final WebClient webClient;

    private static final String CAT_API_URL = "https://meowfacts.herokuapp.com/";

    public Mono<String> getCatFact() {

        return webClient.get()
                .uri(CAT_API_URL)
                .retrieve()
                .bodyToMono(CatResponse.class)
                .map(response -> response.getData().get(0));
    }
}
