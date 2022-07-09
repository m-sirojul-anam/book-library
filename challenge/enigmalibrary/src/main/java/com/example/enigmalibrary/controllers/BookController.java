package com.example.enigmalibrary.controllers;

import com.example.enigmalibrary.constant.ApiURLConstant;
import com.example.enigmalibrary.dto.BookSearchDTO;
import com.example.enigmalibrary.entities.Book;
import com.example.enigmalibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiURLConstant.BOOK)
public class BookController {

    @Autowired
    BookService bookService;

    // Method for Insert new book data
    @PostMapping
    public Book saveBook(@RequestBody Book book){ return bookService.saveBook(book);}

    // Method For get Book By id
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id){ return bookService.getBookById(id);}

    // Method for Update Book data
    @PutMapping
    public void updateBook(@RequestBody Book book){ bookService.saveBook(book);}

    // Method for Soft Delete Book Data
    @PutMapping("/{id}")
    public Book deleteBook(@PathVariable String id){ return bookService.deleteBook(id);}

    // Method for show all book data per page
    @GetMapping
    public Page<Book> getBookPerPage(@RequestBody BookSearchDTO bookSearchDTO,
                                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
                                     Sort sort){

        Pageable pageable = PageRequest.of(page,sizePerPage,sort);

        return bookService.getBookPerPage(pageable, bookSearchDTO);
    }

}
