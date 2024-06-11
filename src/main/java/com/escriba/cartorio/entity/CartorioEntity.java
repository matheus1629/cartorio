package com.escriba.cartorio.entity;

import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer idCartorio;
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;
    @Column(name = "observacao", length = 250)
    private String observacao;
    @ManyToOne()
    @JoinColumn(name = "situacao_id", referencedColumnName = "id", nullable = false)
    private SituacaoEntity situacaoEntity;

    @ManyToMany(mappedBy = "cartorios")
    private Set<AtribuicaoEntity> atribuicoes;


}

