package com.theboredev;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface SocialMediaService {
    CompletableFuture<Collection<Post>> fetchPosts(SocialMediaConfig config);
}
