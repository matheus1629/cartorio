package com.escriba.cartorio.repository;

import com.escriba.cartorio.dto.situacao.SituacaoPaged;
import com.escriba.cartorio.entity.SituacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoRepository extends JpaRepository<SituacaoEntity, String> {

    @Query("SELECT new com.escriba.cartorio.dto.situacao.SituacaoPaged(s.idSituacao, s.nome) FROM SituacaoEntity s")
    Page<SituacaoPaged> findAllPaged(Pageable pageable);

    Optional<SituacaoEntity> findByNome(String situacaoNome);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM CartorioEntity c JOIN c.situacaoEntity s " +
            "WHERE s.idSituacao = :idSituacao")
    boolean existsCartorioBySituacaoId(String idSituacao);
}
