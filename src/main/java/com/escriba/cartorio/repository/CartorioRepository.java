package com.escriba.cartorio.repository;

import com.escriba.cartorio.entity.CartorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartorioRepository extends JpaRepository<CartorioEntity, Integer> {
}
