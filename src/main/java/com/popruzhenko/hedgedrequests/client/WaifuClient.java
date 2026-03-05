package com.popruzhenko.hedgedrequests.client;

import com.popruzhenko.hedgedrequests.dto.WaifuImagesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WaifuClient {

    private final WebClient webClient;

    private static final String WAIFU_API =
            "https://api.waifu.im/images?isNsfw=false&orderBy=Random&page=1&pageSize=1";

    public Mono<String> getRandomWaifuUrl() {

        return webClient.get()
                .uri(WAIFU_API)
                .retrieve()
                .bodyToMono(WaifuImagesResponse.class)
                .map(response -> {

                    if (response.getItems() == null || response.getItems().isEmpty()) {
                        throw new RuntimeException("No waifu image found");
                    }

                    return response.getItems().get(0).getUrl();
                });
    }

    public Mono<byte[]> downloadImage(String url) {

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(byte[].class);
    }
}