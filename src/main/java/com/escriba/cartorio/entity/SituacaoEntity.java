package com.escriba.cartorio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "situacao")
public class SituacaoEntity {

    @Id
    @Column(name = "id", length = 20)
    private String idSituacao;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "situacaoEntity", fetch = FetchType.LAZY)
    private Set<CartorioEntity> cartorios;


}