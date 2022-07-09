package com.example.enigmalibrary.services.impl;

import com.example.enigmalibrary.dto.BookSearchDTO;
import com.example.enigmalibrary.entities.Book;
import com.example.enigmalibrary.repositories.BookRepository;
import com.example.enigmalibrary.services.BookService;
import com.example.enigmalibrary.specification.BookSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Objects;

import static java.lang.Boolean.TRUE;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public
    BookRepository bookRepository;
    public Book book;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book deleteBook(String id) {
        book = bookRepository.findById(id).get();
        book.setDeleted(TRUE);
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> getBookPerPage(Pageable pageable, BookSearchDTO bookSearchDTO) {
        Specification<Book> bookSpecification = BookSpecification.getSpecification(bookSearchDTO);
        return bookRepository.findAll(bookSpecification, pageable);
    }
}
