package com.example.BookmarkApp_v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.BookmarkApp_v2.entity.Bookmark;
import com.example.BookmarkApp_v2.entity.User;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    
    List<Bookmark> findByUserOrderByLastUsedAtDesc(User user);
    List<Bookmark> findByUserOrderByTitleAsc(User user);
    List<Bookmark> findByUserOrderByCreatedAtDesc(User user);
    List<Bookmark> findByUserOrderByFavoriteDescLastUsedAtDesc(User user);
    
    @Query("SELECT b FROM Bookmark b WHERE b.user = :user AND " +
           "(LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.url) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.note) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Bookmark> searchBookmarks(@Param("user") User user, @Param("keyword") String keyword);
    
    List<Bookmark> findByUserAndFavoriteTrue(User user);
    
    long countByUser(User user);
}