package com.popruzhenko.hedgedrequests.scheduler;


import com.popruzhenko.hedgedrequests.service.WaifuService;
import com.popruzhenko.hedgedrequests.service.WaifuWebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WaifuScheduler {

    private final WaifuService waifuService;
    private final WaifuWebSocketService webSocketService;

    @Scheduled(fixedRate = 5000)
    public void sendWaifu() {

        waifuService.generateWaifu()
                .subscribe(
                        webSocketService::sendWaifu,
                        error -> log.error("Failed to generate/send waifu update", error)
                );
    }
}
