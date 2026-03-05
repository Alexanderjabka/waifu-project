package com.popruzhenko.hedgedrequests.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class CatResponse {
    private List<String> data;

}
