package com.escriba.cartorio.repository;

import com.escriba.cartorio.dto.atribuicao.AtribuicaoPaged;
import com.escriba.cartorio.entity.AtribuicaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtribuicaoRepository extends JpaRepository<AtribuicaoEntity, String> {

    @Query("SELECT new com.escriba.cartorio.dto.atribuicao.AtribuicaoPaged(a.idAtribuicao, a.nome) FROM AtribuicaoEntity a")
    Page<AtribuicaoPaged> findAllPaged(Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM CartorioEntity c JOIN c.atribuicoes a " +
            "WHERE a.idAtribuicao = :idAtribuicao")
    boolean existsCartorioByAtribuicaoId(String idAtribuicao);

    Optional<AtribuicaoEntity> findByNome(String atribuicaoName);
}
