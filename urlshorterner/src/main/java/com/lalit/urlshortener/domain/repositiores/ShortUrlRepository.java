package com.lalit.urlshortener.domain.repositiores;

import com.lalit.urlshortener.domain.entities.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
   // @Query("select su from ShortUrl su where su.isPrivate = false order by su.createdAt desc")
    // To solve the n+1 select issue The fetch keyword should come immediately after join, not after the association name.
   //@Query("select su from ShortUrl su left join fetch su.createdBy  where su.isPrivate = false order by su.createdAt desc")
   @Query("select su from ShortUrl su left join fetch su.createdBy  where su.isPrivate = false")
   Page<ShortUrl> findPublicShortUrls(Pageable pageable);

    boolean existsByShortKey(String shortKey);

    Optional<ShortUrl> findByShortKey(String shortKey);

    Page<ShortUrl> findByCreatedById(Long userId, Pageable pageable);


    @Modifying
    void deleteByIdInAndCreatedById(List<Long> ids, Long userId);

    @Query("select u from ShortUrl u left join fetch u.createdBy")
    Page<ShortUrl> findAllShortUrls(Pageable pageable);

    /*Option 2: Using @EntityGraph (cleaner approach)
    @EntityGraph(attributePaths = {"createdBy"})
    @Query("select su from ShortUrl su where su.isPrivate = false order by su.createdAt desc")
    List<ShortUrl> findPublicUrls();*/
}