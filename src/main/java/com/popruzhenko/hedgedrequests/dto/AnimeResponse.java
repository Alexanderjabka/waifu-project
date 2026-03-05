package com.popruzhenko.hedgedrequests.dto;

import com.popruzhenko.hedgedrequests.model.AnimeData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AnimeResponse {
    private AnimeData data;

}
