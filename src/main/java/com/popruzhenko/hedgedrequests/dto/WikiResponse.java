package com.popruzhenko.hedgedrequests.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class WikiResponse {
    private String title;

    private String comment;

}
