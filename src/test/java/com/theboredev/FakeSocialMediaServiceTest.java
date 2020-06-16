package com.theboredev;

import org.junit.Test;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.emptyList;


public class FakeSocialMediaServiceTest {

    private final SocialMediaService socialMediaService = new FakeSocialMediaService();

    @Test
    public void shouldReturnNoPostsIfNoSocialMediaUserHasBeenConfigured() {

        final SocialMediaConfig config = new SocialMediaConfig.Builder()
                .build();

        final CompletableFuture<Collection<Post>> future = socialMediaService.fetchPosts(config);

        assertThat(future.join()).isEqualTo(emptyList());
    }

    @Test
    public void shouldReturnPostsOnlyFromOnePlatformIfOnePlatformHasBeenConfigured() {

        final SocialMediaConfig config = new SocialMediaConfig.Builder()
                .withTwitterUser("myTwitterUser")
                .build();

        final CompletableFuture<Collection<Post>> future = socialMediaService.fetchPosts(config);

        assertThat(future.join()).isEqualTo(List.of(
                new Post("myTwitterUser", "http://url/image.png", "This is Twitter post 1", LocalTime.of(4, 12, 18)),
                new Post("myTwitterUser", "http://url/image.png", "This is Twitter post 2", LocalTime.of(4, 12, 18)),
                new Post("myTwitterUser", "http://url/image.png", "This is Twitter post 3", LocalTime.of(4, 12, 18))
        ));
    }

    @Test
    public void shouldReturnPostsFromEveryConfiguredPlatform() {

        final SocialMediaConfig config = new SocialMediaConfig.Builder()
                .withTwitterUser("myTwitterUser")
                .withFacebookUser("myFacebookUser")
                .withInstagramUser("myInstagramUser")
                .build();

        final CompletableFuture<Collection<Post>> future = socialMediaService.fetchPosts(config);

        assertThat(future.join()).isEqualTo(List.of(
                new Post("myTwitterUser", "http://url/image.png", "This is Twitter post 1", LocalTime.of(4, 12, 18)),
                new Post("myTwitterUser", "http://url/image.png", "This is Twitter post 2", LocalTime.of(4, 12, 18)),
                new Post("myTwitterUser", "http://url/image.png", "This is Twitter post 3", LocalTime.of(4, 12, 18)),
                new Post("myFacebookUser", "http://url/image.png", "This is Facebook post 1", LocalTime.of(4, 12, 18)),
                new Post("myFacebookUser", "http://url/image.png", "This is Facebook post 2", LocalTime.of(4, 12, 18)),
                new Post("myFacebookUser", "http://url/image.png", "This is Facebook post 3", LocalTime.of(4, 12, 18)),
                new Post("myInstagramUser", "http://url/image.png", "This is Instagram post 1", LocalTime.of(4, 12, 18)),
                new Post("myInstagramUser", "http://url/image.png", "This is Instagram post 2", LocalTime.of(4, 12, 18)),
                new Post("myInstagramUser", "http://url/image.png", "This is Instagram post 3", LocalTime.of(4, 12, 18))
        ));
    }
}
