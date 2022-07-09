package com.example.enigmalibrary.utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response<T> {
    private String message;
    private T data;
}
