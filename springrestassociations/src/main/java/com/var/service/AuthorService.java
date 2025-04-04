package com.var.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.var.entity.Author;
import com.var.entity.Book;
import com.var.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author addBook(Long authorId, Book book) {
        Author author = findById(authorId);
        if (author != null) {
        	authorRepository.save(author);
            return save(author);
        }
        return null;
    }

    public Author removeBook(Long authorId, Long bookId) {
        Author author = findById(authorId);
        if (author != null) {
            Book book = author.getBooks().stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
            
            if (book != null) {
                authorRepository.delete(author);
                return save(author);
            }
        }
        return null;
    }
}