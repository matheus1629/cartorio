package com.escriba.cartorio.Service;

import com.escriba.cartorio.dto.PageDTO;
import com.escriba.cartorio.dto.cartorio.CartorioDTO;
import com.escriba.cartorio.dto.cartorio.CartorioPaged;
import com.escriba.cartorio.dto.cartorio.CreateCartorioDTO;
import com.escriba.cartorio.dto.cartorio.UpdateCartorioDTO;
import com.escriba.cartorio.entity.AtribuicaoEntity;
import com.escriba.cartorio.entity.CartorioEntity;
import com.escriba.cartorio.entity.SituacaoEntity;
import com.escriba.cartorio.exception.BusinessRuleException;
import com.escriba.cartorio.repository.CartorioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartorioService {
    private final CartorioRepository cartorioRepository;
    private final ModelMapper modelMapper;
    private final SituacaoService situacaoService;
    private final AtribuicaoService atribuicaoService;

    public PageDTO<CartorioPaged> getPagedCartorios(Integer page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CartorioPaged> cartorioRepositoryAllPaged = cartorioRepository.findAllPaged(pageable);
        List<CartorioPaged> cartorioPageds = new ArrayList<>(cartorioRepositoryAllPaged.getContent());

        return new PageDTO<>(cartorioRepositoryAllPaged.getTotalElements(),
                cartorioRepositoryAllPaged.getTotalPages(),
                page,
                size,
                cartorioPageds);

    }

    public CartorioDTO getCartorioById(Integer idCartorio) {
        return modelMapper.map(getCartorioEntityById(idCartorio), CartorioDTO.class);
    }

    public CartorioDTO createCartorio(CreateCartorioDTO createCartorioDTO) {
        validateIfIdCartorioExist(createCartorioDTO.getIdCartorio());
        validateIfNameCartorioExist(createCartorioDTO.getNome());

        SituacaoEntity situacauEntity = situacaoService.getSituacaoById(createCartorioDTO.getIdSituacao());
        Set<AtribuicaoEntity> atribuicaoEntities = atribuicaoService.getAtribuicoesByIds(createCartorioDTO.getIdAtribuicoes());

        CartorioEntity cartorioEntity = modelMapper.map(createCartorioDTO, CartorioEntity.class);

        cartorioEntity.setSituacaoEntity(situacauEntity);
        cartorioEntity.setAtribuicoes(atribuicaoEntities);

        CartorioEntity cartorioSaved = cartorioRepository.save(cartorioEntity);

        return modelMapper.map(cartorioSaved, CartorioDTO.class);
    }

    public CartorioDTO updateCartorio(UpdateCartorioDTO updateCartorioDTO) {
        CartorioEntity cartorioEntityById = getCartorioEntityById(updateCartorioDTO.getIdCartorio());

        if (!updateCartorioDTO.getNome().equals(cartorioEntityById.getNome())) {
            validateIfNameCartorioExist(updateCartorioDTO.getNome());
        }

        SituacaoEntity situacauEntity = situacaoService.getSituacaoById(updateCartorioDTO.getIdSituacao());
        Set<AtribuicaoEntity> atribuicaoEntities = atribuicaoService.getAtribuicoesByIds(updateCartorioDTO.getIdAtribuicoes());

        CartorioEntity cartorioEntity = modelMapper.map(updateCartorioDTO, CartorioEntity.class);

        cartorioEntity.setSituacaoEntity(situacauEntity);
        cartorioEntity.setAtribuicoes(atribuicaoEntities);

        CartorioEntity cartorioSaved = cartorioRepository.save(cartorioEntity);

        return modelMapper.map(cartorioSaved, CartorioDTO.class);
    }

    public void deleteCartorio(Integer idCartorio) {
        if (!getCartorioEntityById(idCartorio).getAtribuicoes().isEmpty()) {
            throw new BusinessRuleException("Registro utilizado em outro cadastro", HttpStatus.CONFLICT);
        } else {
            cartorioRepository.deleteById(idCartorio);
        }

    }

    private CartorioEntity getCartorioEntityById(Integer idCartorio) {
        return cartorioRepository.findById(idCartorio).orElseThrow(() -> new BusinessRuleException("Cartorio não encontrado", HttpStatus.NOT_FOUND));
    }


    private void validateIfIdCartorioExist(Integer idCartorio) {
        cartorioRepository.findById(idCartorio).ifPresent(cartorio -> {
            throw new BusinessRuleException("Registro já cadastrado", HttpStatus.CONFLICT);
        });
    }

    private void validateIfNameCartorioExist(String cartorioName) {
        cartorioRepository.findByNome(cartorioName).ifPresent(cartorio -> {
            throw new BusinessRuleException("Nome já informado no registro com código " + cartorio.getIdCartorio() + ".", HttpStatus.CONFLICT);
        });
    }

}
