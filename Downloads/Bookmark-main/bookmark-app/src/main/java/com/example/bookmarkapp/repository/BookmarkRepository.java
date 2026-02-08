package com.example.bookmarkapp.repository;

import com.example.bookmarkapp.model.Bookmark;
import com.example.bookmarkapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    
    // ユーザーのブックマークを取得
    List<Bookmark> findByUser(User user);
    
    // ユーザーのお気に入りブックマークを取得
    List<Bookmark> findByUserAndIsFavoriteTrue(User user);
    
    // ユーザーのブックマークを作成日時で並び替え（新しい順）
    List<Bookmark> findByUserOrderByCreatedAtDesc(User user);
    
    // ユーザーのブックマークを作成日時で並び替え（古い順）
    List<Bookmark> findByUserOrderByCreatedAtAsc(User user);
    
    // ユーザーのブックマークをタイトルで並び替え
    List<Bookmark> findByUserOrderByTitleAsc(User user);
}
