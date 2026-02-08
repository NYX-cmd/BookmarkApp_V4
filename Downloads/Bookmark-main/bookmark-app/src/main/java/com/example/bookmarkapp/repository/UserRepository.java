package com.example.bookmarkapp.repository;

import com.example.bookmarkapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // ユーザー名でユーザーを検索
    Optional<User> findByUsername(String username);
    
    // ユーザー名の存在確認
    boolean existsByUsername(String username);
}
