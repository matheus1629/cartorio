package com.escriba.cartorio.controller;

import com.escriba.cartorio.Service.SituacaoService;
import com.escriba.cartorio.dto.PageDTO;
import com.escriba.cartorio.dto.situacao.CreateSituacaoDTO;
import com.escriba.cartorio.dto.situacao.SituacaoDTO;
import com.escriba.cartorio.dto.situacao.SituacaoPaged;
import com.escriba.cartorio.dto.situacao.UpdateSituacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/situacao", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class SituacaoController {

    private final SituacaoService situacaoService;

    @GetMapping("/page/{page}")
    public ResponseEntity<PageDTO<SituacaoPaged>> getByPage(@PathVariable("page") Integer page) {
        return new ResponseEntity<>(situacaoService.getPagedSituacao(page), HttpStatus.OK);
    }

    @GetMapping("/data/{idSituacao}")
    public ResponseEntity<SituacaoDTO> getSituacaoById(@PathVariable("idSituacao") String idSituacao) {
        return new ResponseEntity<>(situacaoService.getSituacaoById(idSituacao), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SituacaoDTO> createSituacao(@RequestBody @Valid CreateSituacaoDTO createSituacaoDTO) {
        return new ResponseEntity<>(situacaoService.createSituacao(createSituacaoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SituacaoDTO> updateSituacao(@RequestBody @Valid UpdateSituacaoDTO updateSituacaoDTO) {
        return new ResponseEntity<>(situacaoService.updateSituacao(updateSituacaoDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{idSituacao}")
    public ResponseEntity<Void> deleteSituacao(@PathVariable String idSituacao) {
        situacaoService.deleteSituacao(idSituacao);
        return ResponseEntity.noContent().build();
    }

}
