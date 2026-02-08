package com.example.bookmarkapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookmarkapp.model.Bookmark;
import com.example.bookmarkapp.model.User;
import com.example.bookmarkapp.service.BookmarkService;
import com.example.bookmarkapp.service.UserService;

@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {
    
    private final BookmarkService bookmarkService;
    private final UserService userService;
    
    @Autowired
    public BookmarkController(BookmarkService bookmarkService, UserService userService) {
        this.bookmarkService = bookmarkService;
        this.userService = userService;
    }
    
    @GetMapping
    public String listBookmarks(@RequestParam(required = false, defaultValue = "newest") String sort,
                               Authentication authentication,
                               Model model) {
        User user = userService.findByUsername(authentication.getName());
        List<Bookmark> bookmarks = bookmarkService.getBookmarks(user, sort);
        
        model.addAttribute("bookmarks", bookmarks);
        model.addAttribute("currentSort", sort);
        model.addAttribute("username", user.getUsername());
        
        return "bookmarks";
    }
    
    @GetMapping("/new")
    public String newBookmarkForm(Model model) {
        model.addAttribute("bookmark", new Bookmark());
        return "bookmark-form";
    }
    
    // ブックマーク保存
    @PostMapping
    public String saveBookmark(@RequestParam String title,
                              @RequestParam String url,
                              @RequestParam(required = false) String description,
                              Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        bookmarkService.saveBookmark(user, title, url, description);
        
        return "redirect:/bookmarks";
    }
    
    // ブックマーク編集フォーム
    @GetMapping("/{id}/edit")
    public String editBookmarkForm(@PathVariable Long id, Model model) {
        Bookmark bookmark = bookmarkService.getBookmarkById(id);
        model.addAttribute("bookmark", bookmark);
        
        return "bookmark-edit";
    }
    
    // ブックマーク更新
    @PostMapping("/{id}")
    public String updateBookmark(@PathVariable Long id,
                                @RequestParam String title,
                                @RequestParam String url,
                                @RequestParam(required = false) String description) {
        bookmarkService.updateBookmark(id, title, url, description);
        
        return "redirect:/bookmarks";
    }
    
    // ブックマーク削除
    @PostMapping("/{id}/delete")
    public String deleteBookmark(@PathVariable Long id) {
        bookmarkService.deleteBookmark(id);
        
        return "redirect:/bookmarks";
    }
    
    // お気に入り切り替え
    @PostMapping("/{id}/favorite")
    public String toggleFavorite(@PathVariable Long id) {
        bookmarkService.toggleFavorite(id);
        
        return "redirect:/bookmarks";
    }
}
