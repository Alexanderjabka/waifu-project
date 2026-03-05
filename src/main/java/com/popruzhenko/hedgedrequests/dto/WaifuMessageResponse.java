package com.popruzhenko.hedgedrequests.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaifuMessageResponse {
    private String imageBase64;

    private String text;
}
