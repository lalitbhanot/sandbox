@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    @EntityGraph(attributePaths = {"createdBy"})
    @Query("SELECT su FROM ShortUrl su WHERE su.isPrivate = false ORDER BY su.createdAt DESC")
    List<ShortUrl> findPublicUrls();
    
    // Multiple associations
    @EntityGraph(attributePaths = {"createdBy", "tags", "category"})
    List<ShortUrl> findByIsPrivateFalse();
    
    // Nested associations
    @EntityGraph(attributePaths = {"createdBy.profile", "createdBy.roles"})
    List<ShortUrl> findAll();
}