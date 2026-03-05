package com.popruzhenko.hedgedrequests.service;

import com.popruzhenko.hedgedrequests.client.WaifuClient;
import com.popruzhenko.hedgedrequests.dto.WaifuMessageResponse;
import com.popruzhenko.hedgedrequests.model.WaifuImage;
import com.popruzhenko.hedgedrequests.repository.WaifuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class WaifuService {

    private final WaifuClient waifuClient;
    private final WaifuRepository waifuRepository;
    private final TextService textService;

    public Mono<WaifuMessageResponse> generateWaifu() {

        return waifuClient.getRandomWaifuUrl()

                .flatMap(url ->
                        waifuClient.downloadImage(url)
                                .map(downloadedImage -> WaifuImage.builder()
                                        .originalUrl(url)
                                        .imageData(downloadedImage.data())
                                        .contentType(downloadedImage.contentType())
                                        .createdAt(LocalDateTime.now())
                                        .build()
                                )
                )

                .flatMap(image -> waifuRepository.save(image).onErrorResume(e -> Mono.just(image)))

                .flatMap(savedImage -> {

                    Mono<String> textMono = textService.getFastestText();

                    Mono<String> base64Mono =
                            Mono.fromCallable(() ->
                                            Base64.getEncoder()
                                                    .encodeToString(savedImage.getImageData())
                                    )
                                    .subscribeOn(Schedulers.boundedElastic());

                            return Mono.zip(textMono, base64Mono)
                            .map(tuple -> WaifuMessageResponse.builder()
                                    .text(tuple.getT1())
                                    .imageBase64(tuple.getT2())
                                    .contentType(savedImage.getContentType())
                                    .build());
                });
    }
}
