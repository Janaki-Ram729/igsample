package com.var.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.var.entity.Book;
import com.var.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}