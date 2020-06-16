package com.theboredev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class TwitterPostFetcher implements PostFetcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterPostFetcher.class);

    @Override
    public CompletableFuture<Collection<Post>> fetchLatestPostsFor(String username) {
        final Executor executor = CompletableFuture.delayedExecutor(100, TimeUnit.MILLISECONDS);
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Fetching posts from Twitter for user {} on thread {}", username, Thread.currentThread().getName());
            return List.of(
                    new Post(username, "http://url/image.png", "This is Twitter post 1", LocalTime.of(4, 12, 18)),
                    new Post(username, "http://url/image.png", "This is Twitter post 2", LocalTime.of(4, 12, 18)),
                    new Post(username, "http://url/image.png", "This is Twitter post 3", LocalTime.of(4, 12, 18))
            );
        }, executor);
    }
}
