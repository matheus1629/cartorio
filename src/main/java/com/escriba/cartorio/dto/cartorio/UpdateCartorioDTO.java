package com.escriba.cartorio.dto.cartorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartorioDTO {
    @Positive
    @NotBlank
    private Integer idCartorio;
    @Size(min = 1, max = 150)
    private String nome;
    @Size(max = 250)
    private String observacao;
    private String idSituacao;
    private List<String> idAtribuicoes;
}
