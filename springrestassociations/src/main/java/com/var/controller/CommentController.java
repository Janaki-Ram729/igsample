package com.var.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.var.entity.Comment;
import com.var.exception.ResourceNotFoundException;
import com.var.repository.CommentRepository;
import com.var.repository.PostRepository;
import jakarta.validation.Valid;
@RestController
public class CommentController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentRepository.findByPostId(postId);
    }
    /*
    [
     {
       "id": 1,
       "text": "excellent",
       "post": {
         "id": 3,
         "title": "java",
         "description": "javaprogramming",
         "content": "coding examples"
       }
     },
     {
       "id": 2,
       "text": "very good",
       "post": {
         "id": 3,
         "title": "java",
         "description": "javaprogramming",
         "content": "coding examples"
       }
     }
   ]
		   */
    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable Long postId, @Valid @RequestBody Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    } /*
    
    
    
    
    {
    	  "id": 1,
    	  "text": "excellent",
    	  "post": {
    	    "id": 3,
    	    "title": "java",
    	    "description": "javaprogramming",
    	    "content": "coding examples"
    	  }
    	}
    	
    	
    	
  "id": 2,
  "text": "very good",
  "post": {
    "id": 3,
    "title": "java",
    "description": "javaprogramming",
    "content": "coding examples"
  }
}
    	*/
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Long postId,
                                 @PathVariable Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

            return commentRepository.save(commentRequest);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }
}
