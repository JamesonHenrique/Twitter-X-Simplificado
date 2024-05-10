package twitterx.simplificado.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long tweetId;
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    private Instant creationTimestamp;

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(
            Long tweetId) {
        this.tweetId =
                tweetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(
            String content) {
        this.content =
                content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(
            User user) {
        this.user =
                user;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(
            Instant creationTimestamp) {
        this.creationTimestamp =
                creationTimestamp;
    }
}
