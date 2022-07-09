package com.enigma.opo.utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response<T> {
    public String message;
    private T data;
}