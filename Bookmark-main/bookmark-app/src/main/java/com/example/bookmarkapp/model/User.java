package com.example.bookmarkapp.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();
    
    // コンストラクタ
    public User() {
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // ゲッター・セッター
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }
    
    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
    
    // ヘルパーメソッド
    public void addBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
        bookmark.setUser(this);
    }
    
    public void removeBookmark(Bookmark bookmark) {
        bookmarks.remove(bookmark);
        bookmark.setUser(null);
    }
}
