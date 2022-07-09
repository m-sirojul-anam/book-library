package com.example.enigmalibrary.repositories;

import com.example.enigmalibrary.entities.Book;
import com.sun.org.apache.xpath.internal.operations.Equals;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor {
}
