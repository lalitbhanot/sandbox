package com.lalit.urlshortener.domain.services;

import com.lalit.urlshortener.AppProperties;
import com.lalit.urlshortener.domain.models.CreateShortUrlCmd;
import com.lalit.urlshortener.domain.models.PagedResult;
import com.lalit.urlshortener.domain.models.ShortUrlDto;
import com.lalit.urlshortener.domain.repositiores.ShortUrlRepository;
import com.lalit.urlshortener.domain.repositiores.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.lalit.urlshortener.domain.entities.ShortUrl;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional(readOnly = true)
public class ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper ;
    private final AppProperties properties ;
    private final UserRepository userRepository;


    public ShortUrlService(ShortUrlRepository shortUrlRepository, EntityMapper entityMapper, AppProperties properties, UserRepository userRepository) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
        this.properties = properties;
        this.userRepository = userRepository;
    }

   /* public List<ShortUrl> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls();
    }*/
    // mapping the entities to DTO

    public PagedResult<ShortUrlDto> findAllPublicShortUrls(int pageNo, int pageSize) {
        pageNo = pageNo > 1? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ShortUrlDto> shortUrlDtoPage = shortUrlRepository.findPublicShortUrls(pageable)
                .map(entityMapper::toShortUrlDto);
        return PagedResult.from(shortUrlDtoPage);
    }
@Transactional
    public ShortUrlDto createShortUrl(CreateShortUrlCmd cmd) {
        var shortKey = generateUniqueShortKey();
        var shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(cmd.originalUrl());
        shortUrl.setShortKey(shortKey);
    if(cmd.userId() == null) {
        shortUrl.setCreatedBy(null);
        shortUrl.setIsPrivate(false);
        shortUrl.setExpiresAt(Instant.now().plus(properties.defaultExpiryInDays(), DAYS));
    } else {
        shortUrl.setCreatedBy(userRepository.findById(cmd.userId()).orElseThrow());
        shortUrl.setIsPrivate(cmd.isPrivate() != null && cmd.isPrivate());
        shortUrl.setExpiresAt(cmd.expirationInDays() != null ? Instant.now().plus(cmd.expirationInDays(), DAYS) : null);
    }
        shortUrl.setClickCount(0L);
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

    @Transactional
    public Optional<ShortUrlDto> accessShortUrl(String shortKey, Long userId) {
        Optional<ShortUrl> shortUrlOptional = shortUrlRepository.findByShortKey(shortKey);
        if(shortUrlOptional.isEmpty()) {
            return Optional.empty();
        }
        ShortUrl shortUrl = shortUrlOptional.get();
        if(shortUrl.getExpiresAt() != null && shortUrl.getExpiresAt().isBefore(Instant.now())) {
            return Optional.empty();
        }
        if(shortUrl.getIsPrivate() != null && shortUrl.getCreatedBy() != null
                && !Objects.equals(shortUrl.getCreatedBy().getId(), userId)) {
            return Optional.empty();
        }
        shortUrl.setClickCount(shortUrl.getClickCount()+1);
        shortUrlRepository.save(shortUrl);
        return shortUrlOptional.map(entityMapper::toShortUrlDto);
    }

    }

