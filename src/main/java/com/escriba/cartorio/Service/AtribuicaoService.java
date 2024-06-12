package com.escriba.cartorio.Service;


import com.escriba.cartorio.dto.PageDTO;
import com.escriba.cartorio.dto.atribuicao.AtribuicaoDTO;
import com.escriba.cartorio.dto.atribuicao.AtribuicaoPaged;
import com.escriba.cartorio.dto.atribuicao.CreateAtribuicaoDTO;
import com.escriba.cartorio.dto.atribuicao.UpdateAtribuicaoDTO;
import com.escriba.cartorio.entity.AtribuicaoEntity;
import com.escriba.cartorio.exception.BusinessRuleException;
import com.escriba.cartorio.repository.AtribuicaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AtribuicaoService {
    private final ModelMapper modelMapper;
    private final AtribuicaoRepository atribuicaoRepository;

    public PageDTO<AtribuicaoPaged> getPagedAtribuicao(Integer page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<AtribuicaoPaged> atribuicaoRepositoryAllPaged = atribuicaoRepository.findAllPaged(pageable);
        List<AtribuicaoPaged> atribuicaoPageds = new ArrayList<>(atribuicaoRepositoryAllPaged.getContent());

        return new PageDTO<>(atribuicaoRepositoryAllPaged.getTotalElements(),
                atribuicaoRepositoryAllPaged.getTotalPages(),
                page,
                size,
                atribuicaoPageds);

    }

    public AtribuicaoDTO getAtribuicaoById(String idAtribuicao) {
        return modelMapper.map(getAtribuicaoEntityById(idAtribuicao), AtribuicaoDTO.class);
    }


    public AtribuicaoDTO createAtribuicao(CreateAtribuicaoDTO createAtribuicaoDTO) {
        validateIfIdAtribuicaoExist(createAtribuicaoDTO.getIdAtribuicao());
        validateIfNameAtribuicaoExist(createAtribuicaoDTO.getNome());

        AtribuicaoEntity atribuicaoEntity = modelMapper.map(createAtribuicaoDTO, AtribuicaoEntity.class);

        AtribuicaoEntity atribuicaoSaved = atribuicaoRepository.save(atribuicaoEntity);

        return modelMapper.map(atribuicaoSaved, AtribuicaoDTO.class);
    }

    public AtribuicaoDTO updateAtribuicao(UpdateAtribuicaoDTO updateAtribuicaoDTO) {
        AtribuicaoEntity atribuicaoEntityById = getAtribuicaoEntityById(updateAtribuicaoDTO.getIdAtribuicao());

        if (!updateAtribuicaoDTO.getNome().equals(atribuicaoEntityById.getNome())) {
            validateIfNameAtribuicaoExist(updateAtribuicaoDTO.getNome());
        }

        AtribuicaoEntity atribuicaoEntity = modelMapper.map(updateAtribuicaoDTO, AtribuicaoEntity.class);

        AtribuicaoEntity atribuicaoSaved = atribuicaoRepository.save(atribuicaoEntity);

        return modelMapper.map(atribuicaoSaved, AtribuicaoDTO.class);
    }

    public void deleteAtribuicao(String idAtribuicao) {
        getAtribuicaoEntityById(idAtribuicao);
        if (atribuicaoRepository.existsCartorioByAtribuicaoId(idAtribuicao)) {
            throw new BusinessRuleException("Registro utilizado em outro cadastro", HttpStatus.CONFLICT);
        } else {
            atribuicaoRepository.deleteById(idAtribuicao);
        }
    }

    private void validateIfNameAtribuicaoExist(String atribuicaoNome) {
        atribuicaoRepository.findByNome(atribuicaoNome).ifPresent(atribuicao -> {
            throw new BusinessRuleException("Nome já informado no registro com código " + atribuicao.getIdAtribuicao() + ".", HttpStatus.CONFLICT);
        });
    }

    private void validateIfIdAtribuicaoExist(String idAtribuicao) {
        atribuicaoRepository.findById(idAtribuicao).ifPresent(atribuicao -> {
            throw new BusinessRuleException("Registro já cadastrado", HttpStatus.CONFLICT);
        });
    }

    private AtribuicaoEntity getAtribuicaoEntityById(String idAtribuicao) {
        return atribuicaoRepository.findById(idAtribuicao).orElseThrow(() -> new BusinessRuleException("Atribuição não encontrada", HttpStatus.NOT_FOUND));
    }

    public Set<AtribuicaoEntity> getAtribuicoesByIds(List<String> idAtribuicoes) {
        return new HashSet<>(atribuicaoRepository.findAllById(idAtribuicoes));
    }


}