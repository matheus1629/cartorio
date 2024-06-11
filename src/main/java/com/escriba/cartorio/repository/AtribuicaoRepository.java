package com.escriba.cartorio.repository;

import com.escriba.cartorio.entity.AtribuicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtribuicaoRepository extends JpaRepository<AtribuicaoEntity, String> {
}
