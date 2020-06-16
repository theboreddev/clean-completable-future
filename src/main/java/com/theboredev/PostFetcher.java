package com.theboredev;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface PostFetcher {
    CompletableFuture<Collection<Post>> fetchLatestPostsFor(String username);
}
