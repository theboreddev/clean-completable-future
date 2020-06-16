package com.theboredev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PostFetcherFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostFetcherFactory.class);

    private static final Map<SocialMediaPlatform, PostFetcher> FACTORY = Map.of(
            SocialMediaPlatform.TWITTER, new TwitterPostFetcher(),
            SocialMediaPlatform.FACEBOOK, new FacebookPostFetcher(),
            SocialMediaPlatform.INSTAGRAM, new InstagramPostFetcher()
    );

    public static PostFetcher postFetcherFrom(SocialMediaPlatform platform) {
        LOGGER.info("Returning PostFetcher implementation for platform {}", platform);
        return FACTORY.get(platform);
    }
}
