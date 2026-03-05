package com.popruzhenko.hedgedrequests.scheduler;


import com.popruzhenko.hedgedrequests.service.WaifuService;
import com.popruzhenko.hedgedrequests.service.WaifuWebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaifuScheduler {

    private final WaifuService waifuService;
    private final WaifuWebSocketService webSocketService;

    @Scheduled(fixedRate = 5000)
    public void sendWaifu() {

        waifuService.generateWaifu()
                .subscribe(webSocketService::sendWaifu);
    }
}