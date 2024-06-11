package com.escriba.cartorio.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "atribuicao")
public class AtribuicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 20)
    private String idAtribuicao;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    private Boolean situacao;
    @ManyToMany
    @JoinTable(
            name = "cartorio_atribuicao",
            joinColumns = @JoinColumn(name = "atribuicao_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cartorio_id", referencedColumnName = "id")
    )
    private Set<CartorioEntity> cartorios;

}


