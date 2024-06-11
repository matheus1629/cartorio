package com.escriba.cartorio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T>{
    private Long totalElements;
    private Integer numberOfPages;
    private Integer page;
    private Integer size;
    private List<T> elements;
}