package com.popruzhenko.hedgedrequests.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "waifus")
public class WaifuImage {
    @Id
    private String id;

    private String originalUrl;

    private byte[] imageData;

    private String contentType;

    private LocalDateTime createdAt;

}
