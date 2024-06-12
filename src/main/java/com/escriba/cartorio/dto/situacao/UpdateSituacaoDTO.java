package com.escriba.cartorio.dto.situacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSituacaoDTO {
    @Size(max = 20)
    @NotBlank
    private String idSituacao;
    @NotBlank
    @Size(max = 50)
    private String nome;
}
