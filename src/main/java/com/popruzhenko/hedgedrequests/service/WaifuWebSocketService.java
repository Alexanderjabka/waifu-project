package com.popruzhenko.hedgedrequests.service;


import com.popruzhenko.hedgedrequests.dto.WaifuMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaifuWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendWaifu(WaifuMessageResponse response) {

        messagingTemplate.convertAndSend("/topic/waifu", response);
    }
}
