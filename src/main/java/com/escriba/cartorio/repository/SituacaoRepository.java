package com.escriba.cartorio.repository;

import com.escriba.cartorio.entity.SituacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoRepository extends JpaRepository<SituacaoEntity, String> {
}
