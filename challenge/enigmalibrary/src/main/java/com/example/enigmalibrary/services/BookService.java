package com.example.enigmalibrary.services;

import com.example.enigmalibrary.dto.BookSearchDTO;
import com.example.enigmalibrary.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    public Book saveBook(Book book);
    public Book getBookById(String id);
    public Book deleteBook(String id);
    public Page<Book> getBookPerPage(Pageable pageable, BookSearchDTO bookSearchDTO);
}
