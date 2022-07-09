package com.example.enigmalibrary.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Publisher is required")
    private String publisher;

    private Integer publishYear;

    @NotBlank(message = "Author is required")
    private String author;

    private Integer stock = 0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean deleted = FALSE;

}
