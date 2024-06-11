package com.escriba.cartorio.Service;

import com.escriba.cartorio.entity.AtribuicaoEntity;
import com.escriba.cartorio.repository.AtribuicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AtribuicaoService {

    private final AtribuicaoRepository atribuicaoRepository;

    public Set<AtribuicaoEntity> getAtribuicoesByIds(List<String> idAtribuicoes) {
        return new HashSet<>(atribuicaoRepository.findAllById(idAtribuicoes));
    }
}
