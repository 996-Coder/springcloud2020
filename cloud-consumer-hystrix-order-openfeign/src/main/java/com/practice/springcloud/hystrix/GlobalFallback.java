package com.practice.springcloud.hystrix;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author michael.zhang
 * @date 5/14/2020 5:12 PM
 * @desc
 */
@Component
public class GlobalFallback {
    public ResponseEntity globalFallbackMethod(String id) {
        return ResponseEntity.ok("Use global fallback method.");
    }
}
