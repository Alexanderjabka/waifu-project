package com.popruzhenko.hedgedrequests.client;

import com.popruzhenko.hedgedrequests.dto.AnimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AnimeClient {

    private final WebClient webClient;

    private static final String ANIME_API_URL = "https://api.animechan.io/v1/quotes/random";

    public Mono<String> getAnimeQuote() {

        return webClient.get()
                .uri(ANIME_API_URL)
                .retrieve()
                .bodyToMono(AnimeResponse.class)
                .map(response -> response.getData().getContent());
    }
}
