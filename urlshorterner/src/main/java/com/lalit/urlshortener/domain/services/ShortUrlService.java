package com.lalit.urlshortener.domain.services;

import com.lalit.urlshortener.AppProperties;
import com.lalit.urlshortener.domain.models.CreateShortUrlCmd;
import com.lalit.urlshortener.domain.models.ShortUrlDto;
import com.lalit.urlshortener.domain.repositiores.ShortUrlRepository;
import org.springframework.stereotype.Service;
import com.lalit.urlshortener.domain.entities.ShortUrl;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional(readOnly = true)
public class ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper ;
    private final AppProperties properties ;


    public ShortUrlService(ShortUrlRepository shortUrlRepository, EntityMapper entityMapper, AppProperties properties) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
        this.properties = properties;
    }

   /* public List<ShortUrl> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls();
    }*/
    // mapping the entities to DTO

    public List<ShortUrlDto> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls()
                .stream().map(entityMapper::toShortUrlDto).toList();
    }
@Transactional
    public ShortUrlDto createShortUrl(CreateShortUrlCmd cmd) {
        var shortKey = generateUniqueShortKey();
        var shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(cmd.originalUrl());
        shortUrl.setShortKey(shortKey);
        shortUrl.setCreatedBy(null);
        shortUrl.setIsPrivate(false);
        shortUrl.setClickCount(0L);
        shortUrl.setExpiresAt(Instant.now().plus(properties.defaultExpiryInDays(), DAYS));
        shortUrl.setCreatedAt(Instant.now());
        shortUrlRepository.save(shortUrl);
        return entityMapper.toShortUrlDto(shortUrl);
    }

    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = generateRandomShortKey();
        } while (shortUrlRepository.existsByShortKey(shortKey));
        return shortKey;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_KEY_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomShortKey() {
        StringBuilder sb = new StringBuilder(SHORT_KEY_LENGTH);
        for (int i = 0; i < SHORT_KEY_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
    }

