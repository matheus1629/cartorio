package com.escriba.cartorio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cartorio")
public class CartorioEntity {

    @Id
    @Column(name = "id")
    private Integer idCartorio;
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;
    @Column(name = "observacao", length = 250)
    private String observacao;
    @ManyToOne()
    @JoinColumn(name = "situacao_id", referencedColumnName = "id", nullable = false)
    private SituacaoEntity situacaoEntity;

    @ManyToMany()
    @JoinTable(
            name = "cartorio_atribuicao",
            joinColumns = @JoinColumn(name = "cartorio_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "atribuicao_id", referencedColumnName = "id")
    )
    private Set<AtribuicaoEntity> atribuicoes;


}

