package com.rg.user.service.externals;

import com.rg.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface IRatingService {
    @PostMapping("/rating")
    Rating createRating(Rating rating);
}
