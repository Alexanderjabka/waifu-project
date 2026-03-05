package com.popruzhenko.hedgedrequests.client;

import com.popruzhenko.hedgedrequests.dto.WikiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WikiClient {

    private final WebClient webClient;

    private static final String WIKI_API_URL =
            "https://en.wikipedia.org/api/rest_v1/page/title/";

    public Mono<String> getPlanetComment(String planet) {

        return webClient.get()
                .uri(WIKI_API_URL + planet)
                .retrieve()
                .bodyToMono(WikiResponse.class)
                .map(WikiResponse::getComment);
    }
}
