package com.theboredev;

import java.time.LocalTime;
import java.util.Objects;

public class Post {
    private final String author;
    private final String imageUrl;
    private final String text;
    private final LocalTime createdAt;

    public Post(String author, String imageUrl, String text, LocalTime createdAt) {
        this.author = author;
        this.imageUrl = imageUrl;
        this.text = text;
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(author, post.author) &&
                Objects.equals(imageUrl, post.imageUrl) &&
                Objects.equals(text, post.text) &&
                Objects.equals(createdAt, post.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, imageUrl, text, createdAt);
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
