package com.example.BookmarkApp_v2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shared_bookmarks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedBookmark {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmark_id", nullable = false)
    private Bookmark bookmark;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User sharedBy;
    
    @Column(unique = true, nullable = false)
    private String shareCode;
    
    @Column(nullable = false)
    private LocalDateTime sharedAt = LocalDateTime.now();
    
    @Column(nullable = false)
    private boolean active = true;
    
    private int viewCount = 0;
    
    public void incrementViewCount() {
        this.viewCount++;
    }
}