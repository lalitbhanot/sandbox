package com.lalit.urlshortener.domain.services;

import com.lalit.urlshortener.domain.models.ShortUrlDto;
import com.lalit.urlshortener.domain.repositiores.ShortUrlRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper ;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, EntityMapper entityMapper) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;

    }

   /* public List<ShortUrl> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls();
    }*/
    // mapping the entities to DTO

    public List<ShortUrlDto> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls()
                .stream().map(entityMapper::toShortUrlDto).toList();
    }

}