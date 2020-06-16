package com.theboredev;

import java.util.HashMap;
import java.util.Map;

public class SocialMediaConfig {
    private final Map<SocialMediaPlatform, String> users;

    private SocialMediaConfig(Builder builder) {
        this.users = builder.users;
    }

    static class Builder {
        private final Map<SocialMediaPlatform, String> users = new HashMap<>();

        public Builder withTwitterUser(String username) {
            users.put(SocialMediaPlatform.TWITTER, username);
            return this;
        }

        public Builder withFacebookUser(String username) {
            users.put(SocialMediaPlatform.FACEBOOK, username);
            return this;
        }

        public Builder withInstagramUser(String username) {
            users.put(SocialMediaPlatform.INSTAGRAM, username);
            return this;
        }

        public SocialMediaConfig build() {
            return new SocialMediaConfig(this);
        }
    }

    public String getUserFor(SocialMediaPlatform platform) {
        return users.get(platform);
    }

    @Override
    public String toString() {
        return "SocialMediaConfig{" +
                "users=" + users +
                '}';
    }
}
