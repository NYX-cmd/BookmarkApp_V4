package com.example.bookmarkapp.service;

import com.example.bookmarkapp.model.User;
import com.example.bookmarkapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // ユーザー登録
    public User registerUser(String username, String password) {
        // ユーザー名の重複チェック
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("このユーザー名は既に使用されています");
        }
        
        // パスワードをハッシュ化
        String encodedPassword = passwordEncoder.encode(password);
        
        // ユーザーを作成して保存
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        
        return userRepository.save(user);
    }
    
    // ユーザー名でユーザーを取得
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
    }
}
