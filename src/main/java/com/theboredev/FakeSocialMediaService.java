package com.theboredev;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class FakeSocialMediaService implements SocialMediaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FakeSocialMediaService.class);


    @Override
    public CompletableFuture<Collection<Post>> fetchPosts(SocialMediaConfig config) {
        LOGGER.info("Fetching posts for {} on thread {}", config, Thread.currentThread().getName());

        final List<CompletableFuture<Collection<Post>>> futures = Stream.of(SocialMediaPlatform.values())
                .filter(hasUserForThatPlatform(config))
                .map(platform -> {
                    final PostFetcher fetcher = PostFetcherFactory.postFetcherFrom(platform);
                    return fetcher.fetchLatestPostsFor(config.getUserFor(platform));
                })
                .collect(toList());

        return futures.stream()
                .reduce(combineApiCalls())
                .orElse(CompletableFuture.completedFuture(emptyList()));
    }


    private Predicate<SocialMediaPlatform> hasUserForThatPlatform(SocialMediaConfig config) {
        return platform -> config.getUserFor(platform) != null;
    }

    private BinaryOperator<CompletableFuture<Collection<Post>>> combineApiCalls() {
        return (c1, c2) -> c1
                .thenCombine(c2, (posts1, posts2) -> {
                    return Stream.concat(posts1.stream(), posts2.stream()).collect(toList());
                });
    }
}
