package com.escriba.cartorio.dto.cartorio;

import com.escriba.cartorio.dto.atribuicao.AtribuicaoDTO;
import com.escriba.cartorio.dto.situacao.SituacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartorioDTO {
    private Integer idCartorio;
    private String nome;
    private String observacao;
    private SituacaoDTO situacao;
    private Set<AtribuicaoDTO> atribuicoes;
}

