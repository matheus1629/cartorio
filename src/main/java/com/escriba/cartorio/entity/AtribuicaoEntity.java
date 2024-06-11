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
    @Column(name = "id", length = 20)
    private String idAtribuicao;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    private Boolean situacao;
    @ManyToMany(mappedBy = "atribuicoes")
    private Set<CartorioEntity> cartorios;

}


