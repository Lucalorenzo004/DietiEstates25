package com.ctlfab.estatesearch.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineCacheConfig {

    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                "location-cache",
                "poi-cache",
                "addon-cache",
                "category-cache",
                "estate-cache");
        cacheManager.setCaffeine(caffeineBuilder());

        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineBuilder(){
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterAccess(60, TimeUnit.MINUTES)
                .recordStats();
    }
}
