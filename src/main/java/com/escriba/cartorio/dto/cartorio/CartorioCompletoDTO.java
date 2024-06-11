package com.escriba.cartorio.dto.cartorio;

import com.escriba.cartorio.dto.atribuicao.AtribuicaoCompletoDTO;
import com.escriba.cartorio.dto.situacao.SituacaoCompletoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartorioCompletoDTO {
    private Integer idCartorio;
    private String nome;
    private String observacao;
    private SituacaoCompletoDTO situacao;
    private Set<AtribuicaoCompletoDTO> atribuicoes;
}

