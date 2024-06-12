package com.escriba.cartorio.dto.atribuicao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAtribuicaoDTO {
    @Size(max = 20)
    @NotBlank
    private String idAtribuicao;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @NotNull
    private Boolean situacao;
}
