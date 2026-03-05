package com.popruzhenko.hedgedrequests.controller;

import com.popruzhenko.hedgedrequests.dto.WaifuMessageResponse;
import com.popruzhenko.hedgedrequests.service.WaifuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class WaifuController {

    private final WaifuService waifuService;

    @GetMapping("/waifu")
    public Mono<WaifuMessageResponse> getWaifu() {
        return waifuService.generateWaifu();
    }
}
