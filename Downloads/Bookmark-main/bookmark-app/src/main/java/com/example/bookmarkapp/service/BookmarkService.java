package com.example.bookmarkapp.service;

import com.example.bookmarkapp.model.Bookmark;
import com.example.bookmarkapp.model.User;
import com.example.bookmarkapp.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BookmarkService {
    
    private final BookmarkRepository bookmarkRepository;
    
    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
    
    // ブックマークを保存
    public Bookmark saveBookmark(User user, String title, String url, String description) {
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle(title);
        bookmark.setUrl(url);
        bookmark.setDescription(description);
        bookmark.setUser(user);
        bookmark.setIsFavorite(false);
        
        return bookmarkRepository.save(bookmark);
    }
    
    // ブックマークを更新
    public Bookmark updateBookmark(Long id, String title, String url, String description) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ブックマークが見つかりません"));
        
        bookmark.setTitle(title);
        bookmark.setUrl(url);
        bookmark.setDescription(description);
        
        return bookmarkRepository.save(bookmark);
    }
    
    // ブックマークを削除
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }
    
    // お気に入りの切り替え
    public Bookmark toggleFavorite(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ブックマークが見つかりません"));
        
        bookmark.toggleFavorite();
        
        return bookmarkRepository.save(bookmark);
    }
    
    // ユーザーのブックマークを取得（並び替えオプション付き）
    public List<Bookmark> getBookmarks(User user, String sortBy) {
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "newest";
        }
        
        switch (sortBy) {
            case "oldest":
                return bookmarkRepository.findByUserOrderByCreatedAtAsc(user);
            case "title":
                return bookmarkRepository.findByUserOrderByTitleAsc(user);
            case "favorites":
                return bookmarkRepository.findByUserAndIsFavoriteTrue(user);
            case "newest":
            default:
                return bookmarkRepository.findByUserOrderByCreatedAtDesc(user);
        }
    }
    
    // IDでブックマークを取得
    public Bookmark getBookmarkById(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ブックマークが見つかりません"));
    }
}
