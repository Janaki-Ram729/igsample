package com.var.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.var.entity.Author;
import com.var.entity.Book;
import com.var.service.AuthorService;

@RestController
@RequestMapping("/authors")                        //http://localhost:8080/authors
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    /*
    {
    	  "name": "George Orwell"
    }
    */
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    /*  /authors/{authorId}/books
    {
    	  "title": "java"
    	}
    	
    	*/
    @PostMapping("/{authorId}/books")
    public Author addBook(@PathVariable Long authorId, @RequestBody Book book) {
        return authorService.addBook(authorId, book);
    }

    @DeleteMapping("/{authorId}/books/{bookId}")
    public Author removeBook(@PathVariable Long authorId, @PathVariable Long bookId) {
        return authorService.removeBook(authorId, bookId);
    }
}