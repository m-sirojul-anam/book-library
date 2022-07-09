package com.example.enigmalibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.lang.Boolean.FALSE;

@Getter
@Setter
@Entity
@Table(name = "mst_book")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String title;

    private String genre;

    private String publisher;

    private Integer publishYear;

    private String author;

    private Integer stock = 0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean deleted = FALSE;

}
