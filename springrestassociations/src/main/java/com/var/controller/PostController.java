package com.var.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.var.entity.Post;
import com.var.repository.PostRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts") //http://localhost:8080/posts
    public List<Post> getAllPosts() {
        List<Post> posts=new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    
    
    @PostMapping("/posts")
    @Transactional
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }
   

    @PutMapping("/posts/{postId}")
    @Transactional
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post post) {
        return postRepository.save(post);
    }


    @DeleteMapping("/posts/{postId}")
    @Transactional
    public void deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
    }
}
