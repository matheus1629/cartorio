package com.escriba.cartorio.Service;

import com.escriba.cartorio.entity.SituacaoEntity;
import com.escriba.cartorio.repository.SituacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SituacaoService {

    private final SituacaoRepository situacaoRepository;
    public SituacaoEntity getSituacaoById(String idSituacao) {
        return situacaoRepository.getOne(idSituacao);
    }
}
