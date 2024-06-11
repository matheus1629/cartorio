package com.escriba.cartorio.dto.cartorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartorioPaged {
    private Integer idCartorio;
    private String nome;
}
