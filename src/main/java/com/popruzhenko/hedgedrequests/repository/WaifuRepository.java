package com.popruzhenko.hedgedrequests.repository;

import com.popruzhenko.hedgedrequests.model.WaifuImage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WaifuRepository extends ReactiveMongoRepository<WaifuImage, String> {
    Mono<WaifuImage> findFirstByOrderByCreatedAtDesc();

}
