package com.example.enigmalibrary.services.impl;

import com.example.enigmalibrary.dto.BookSearchDTO;
import com.example.enigmalibrary.entities.Book;
import com.example.enigmalibrary.repositories.BookRepository;
import com.example.enigmalibrary.specification.BookSpecification;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.lang.Boolean.FALSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookServiceImpl;

    private BookSearchDTO bookSearchDTO;
    private Pageable pageable;
    private Book book;

    @BeforeEach
    void setup(){
        book = new Book("s123","Aini","education","gintama",2010,"pratama",10,FALSE);
        bookSearchDTO = new BookSearchDTO("Aini","","",null,null,"",FALSE);
        pageable = PageRequest.of(0,5, Sort.by("id"));
    }

    @AfterEach
    void cleanUp(){
        bookSearchDTO = new BookSearchDTO();
        pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }

    @Test
    void getBookPerPage() {
        Specification<Book> bookSpecification = BookSpecification.getSpecification(bookSearchDTO);
        System.out.println(bookSpecification);
        Page<Book> bookPage = bookRepository.findAll(bookSpecification, pageable);

        lenient().when(bookRepository.findAll(bookSpecification, pageable)).thenReturn(bookPage);

        Page<Book> bookPage1 = bookServiceImpl.getBookPerPage(pageable, bookSearchDTO);
        assertEquals(bookPage, bookPage1);
        assertEquals(5, pageable.getPageSize());
    }
}