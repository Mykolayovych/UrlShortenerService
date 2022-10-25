package com.example.urlshortenerservice.shortUrlService.model;

import com.example.urlshortenerservice.userService.model.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "short_url")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ShortUrl {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "hash", nullable = false, unique = true)
    private String hash;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private ZonedDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static ShortUrl create(String hash, String originalUrl, User user) {
        return ShortUrl.builder()
                .hash(hash)
                .originalUrl(originalUrl)
                .createdAt(ZonedDateTime.now())
                .user(user)
                .build();
    }
}
