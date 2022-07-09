package com.example.enigmalibrary.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BookSearchDTO {
    private String searchBookTitle;
    private String searchBookGenre;
    private String searchBookPublisher;
    private Integer searchBookPublishStartYear;
    private Integer searchBookPublishEndYear;
    private String searchBookAuthor;
    private Boolean searchBookDeleted;
}
