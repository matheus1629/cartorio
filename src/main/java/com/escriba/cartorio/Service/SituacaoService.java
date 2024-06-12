package com.escriba.cartorio.Service;

import com.escriba.cartorio.dto.PageDTO;
import com.escriba.cartorio.dto.situacao.CreateSituacaoDTO;
import com.escriba.cartorio.dto.situacao.SituacaoDTO;
import com.escriba.cartorio.dto.situacao.SituacaoPaged;
import com.escriba.cartorio.dto.situacao.UpdateSituacaoDTO;
import com.escriba.cartorio.entity.SituacaoEntity;
import com.escriba.cartorio.exception.BusinessRuleException;
import com.escriba.cartorio.repository.SituacaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SituacaoService {
    private final ModelMapper modelMapper;
    private final SituacaoRepository situacaoRepository;

    public PageDTO<SituacaoPaged> getPagedSituacao(Integer page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<SituacaoPaged> situacaoRepositoryAllPaged = situacaoRepository.findAllPaged(pageable);
        List<SituacaoPaged> situacaoPageds = new ArrayList<>(situacaoRepositoryAllPaged.getContent());

        return new PageDTO<>(situacaoRepositoryAllPaged.getTotalElements(),
                situacaoRepositoryAllPaged.getTotalPages(),
                page,
                size,
                situacaoPageds);

    }

    public SituacaoDTO getSituacaoById(String idSituacao) {
        return modelMapper.map(getSituacaoEntityById(idSituacao), SituacaoDTO.class);
    }

    public SituacaoDTO createSituacao(CreateSituacaoDTO createSituacaoDTO) {
        validateIfIdSituacaoExist(createSituacaoDTO.getIdSituacao());
        validateIfNameSituacaoExist(createSituacaoDTO.getNome());

        SituacaoEntity situacaoEntity = modelMapper.map(createSituacaoDTO, SituacaoEntity.class);

        SituacaoEntity situacaoSaved = situacaoRepository.save(situacaoEntity);

        return modelMapper.map(situacaoSaved, SituacaoDTO.class);
    }

    public SituacaoDTO updateSituacao(UpdateSituacaoDTO updateSituacaoDTO) {
        SituacaoEntity situacaoEntityById = getSituacaoEntityById(updateSituacaoDTO.getIdSituacao());

        if (!updateSituacaoDTO.getNome().toLowerCase().equals(situacaoEntityById.getNome().toLowerCase())) {
            validateIfNameSituacaoExist(updateSituacaoDTO.getNome());
        }

        SituacaoEntity situacaoEntity = modelMapper.map(updateSituacaoDTO, SituacaoEntity.class);

        SituacaoEntity situacaoSaved = situacaoRepository.save(situacaoEntity);

        return modelMapper.map(situacaoSaved, SituacaoDTO.class);
    }

    public void deleteSituacao(String idSituacao) {
        getSituacaoEntityById(idSituacao);
        if (situacaoRepository.existsCartorioBySituacaoId(idSituacao)) {
            throw new BusinessRuleException("Registro utilizado em outro cadastro", HttpStatus.CONFLICT);
        } else {
            situacaoRepository.deleteById(idSituacao);
        }
    }

    public SituacaoEntity getSituacaoEntityById(String idSituacao) {
        return situacaoRepository.findById(idSituacao).orElseThrow(() -> new BusinessRuleException("Situacao não encontrada", HttpStatus.NOT_FOUND));
    }

    private void validateIfIdSituacaoExist(String idSituacao) {
        situacaoRepository.findById(idSituacao).ifPresent(situacao -> {
            throw new BusinessRuleException("Registro já cadastrado", HttpStatus.CONFLICT);
        });
    }

    private void validateIfNameSituacaoExist(String situacaoNome) {
        situacaoRepository.findByNome(situacaoNome).ifPresent(situacao -> {
            throw new BusinessRuleException("Nome já informado no registro com código " + situacao.getIdSituacao() + ".", HttpStatus.CONFLICT);
        });
    }


}
