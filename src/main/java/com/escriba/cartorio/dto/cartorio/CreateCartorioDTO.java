package com.escriba.cartorio.dto.cartorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartorioDTO {
    @Positive
    @NotNull
    private Integer idCartorio;
    @Size(min = 1, max = 150)
    @NotBlank
    private String nome;
    @Size(max = 250)
    private String observacao;
    @NotBlank
    private String idSituacao;
    @Size(min = 1)
    private List<String> idAtribuicoes;
}

