package com.escriba.cartorio.Service;

import com.escriba.cartorio.dto.atribuicao.AtribuicaoCompletoDTO;
import com.escriba.cartorio.dto.cartorio.CartorioCompletoDTO;
import com.escriba.cartorio.dto.situacao.SituacaoCompletoDTO;
import com.escriba.cartorio.entity.AtribuicaoEntity;
import com.escriba.cartorio.entity.CartorioEntity;
import com.escriba.cartorio.repository.CartorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartorioService {
    private final CartorioRepository cartorioRepository;

    public CartorioCompletoDTO getCartorioById(Integer id) {
        CartorioEntity cartorioEntity = cartorioRepository.findById(id).orElseThrow();

        CartorioCompletoDTO cartorioCompletoDTO = new CartorioCompletoDTO();
        cartorioCompletoDTO.setIdCartorio(cartorioEntity.getIdCartorio());
        cartorioCompletoDTO.setNome(cartorioEntity.getNome());
        cartorioCompletoDTO.setObservacao(cartorioEntity.getObservacao());

        SituacaoCompletoDTO situacaoCompletoDTO = new SituacaoCompletoDTO(cartorioEntity.getSituacaoEntity().getIdSituacao(), cartorioEntity.getSituacaoEntity().getNome());
        cartorioCompletoDTO.setSituacao(situacaoCompletoDTO);

        Set<AtribuicaoCompletoDTO> atribuicoesDTO = cartorioEntity.getAtribuicoes()
                .stream()
                .map(atribuicao -> {
                    System.out.println(atribuicao.getCartorios().toString());
                    AtribuicaoCompletoDTO atribuicaoCompletoDTO = new AtribuicaoCompletoDTO();
                    atribuicaoCompletoDTO.setIdAtribuicao(atribuicao.getIdAtribuicao());
                    atribuicaoCompletoDTO.setNome(atribuicao.getNome());
                    atribuicaoCompletoDTO.setSituacao(atribuicao.getSituacao());
                    return atribuicaoCompletoDTO;
                })
                .collect(Collectors.toSet());

        cartorioCompletoDTO.setAtribuicoes(atribuicoesDTO);

        return cartorioCompletoDTO;
    }
}
