package com.escriba.cartorio.repository;

import com.escriba.cartorio.dto.cartorio.CartorioPaged;
import com.escriba.cartorio.entity.CartorioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartorioRepository extends JpaRepository<CartorioEntity, Integer> {

    @Query("SELECT new com.escriba.cartorio.dto.cartorio.CartorioPaged(c.idCartorio, c.nome) FROM CartorioEntity c")
    Page<CartorioPaged> findAllPaged(Pageable pageable);

}
