package com.escriba.cartorio.Service;

import com.escriba.cartorio.dto.PageDTO;
import com.escriba.cartorio.dto.cartorio.CartorioCompletoDTO;
import com.escriba.cartorio.dto.cartorio.CartorioPaged;
import com.escriba.cartorio.entity.CartorioEntity;
import com.escriba.cartorio.repository.CartorioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartorioService {
    private final CartorioRepository cartorioRepository;
    private final ModelMapper modelMapper;

    private final Integer size = 10;

    public CartorioCompletoDTO getCartorioById(Integer id) {
        CartorioEntity cartorioEntity = cartorioRepository.findById(id).orElseThrow();
        return modelMapper.map(cartorioEntity, CartorioCompletoDTO.class);
    }

    public PageDTO<CartorioPaged> getPagedCartorios(Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CartorioPaged> cartorioRepositoryAllPaged = cartorioRepository.findAllPaged(pageable);
        List<CartorioPaged> cartorioPageds = cartorioRepositoryAllPaged.getContent().stream()
                .collect(Collectors.toList());

        return new PageDTO<>(cartorioRepositoryAllPaged.getTotalElements(),
                cartorioRepositoryAllPaged.getTotalPages(),
                page,
                size,
                cartorioPageds);

    }
}
