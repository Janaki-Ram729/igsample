package com.var.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.var.entity.Comment;
@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
    Optional<Comment> findByIdAndPostId(Long id, Long postId);
}
