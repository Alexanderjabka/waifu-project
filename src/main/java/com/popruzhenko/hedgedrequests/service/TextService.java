package com.popruzhenko.hedgedrequests.service;


import com.popruzhenko.hedgedrequests.client.AnimeClient;
import com.popruzhenko.hedgedrequests.client.CatClient;
import com.popruzhenko.hedgedrequests.client.WikiClient;
import com.popruzhenko.hedgedrequests.model.Planets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class TextService {

    private final CatClient catClient;
    private final AnimeClient animeClient;
    private final WikiClient wikiClient;

    public Mono<String> getFastestText() {

        String planet = getRandomPlanet();

        Mono<String> wiki =
                Mono.defer(() ->
                                wikiClient.getPlanetComment(planet)
                                        .map(comment -> planet + ": " + comment)
                        )
                        .onErrorResume(e -> Mono.empty())
                        .cache();

        Mono<String> cats =
                catClient.getCatFact()
                        .onErrorResume(e -> wiki);

        Mono<String> anime =
                animeClient.getAnimeQuote()
                        .onErrorResume(e -> wiki);

        return Mono.firstWithSignal(anime, cats)
                .switchIfEmpty(wiki)
                .switchIfEmpty(
                        Mono.just("All services unavailable")
                );
    }

    private String getRandomPlanet() {
        return Planets.values()[
                ThreadLocalRandom.current().nextInt(Planets.values().length)
                ].name();
    }
}
