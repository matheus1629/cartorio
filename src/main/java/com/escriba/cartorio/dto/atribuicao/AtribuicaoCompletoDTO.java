package com.escriba.cartorio.dto.atribuicao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtribuicaoCompletoDTO {
    private String idAtribuicao;
    private String nome;
    private Boolean situacao;
}
