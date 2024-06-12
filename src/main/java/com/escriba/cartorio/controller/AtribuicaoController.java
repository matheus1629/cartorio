package com.escriba.cartorio.controller;

import com.escriba.cartorio.Service.AtribuicaoService;
import com.escriba.cartorio.dto.PageDTO;
import com.escriba.cartorio.dto.atribuicao.AtribuicaoDTO;
import com.escriba.cartorio.dto.atribuicao.AtribuicaoPaged;
import com.escriba.cartorio.dto.atribuicao.CreateAtribuicaoDTO;
import com.escriba.cartorio.dto.atribuicao.UpdateAtribuicaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/atribuicao", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class AtribuicaoController {

    private final AtribuicaoService atribuicaoService;

    @GetMapping("/page/{page}")
    public ResponseEntity<PageDTO<AtribuicaoPaged>> getByPage(@PathVariable("page") Integer page) {
        return new ResponseEntity<>(atribuicaoService.getPagedAtribuicao(page), HttpStatus.OK);
    }

    @GetMapping("/data/{idAtribuicao}")
    public ResponseEntity<AtribuicaoDTO> getAtribuicaoById(@PathVariable("idAtribuicao") String idAtribuicao) {
        return new ResponseEntity<>(atribuicaoService.getAtribuicaoById(idAtribuicao), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AtribuicaoDTO> createAtribuicao(@RequestBody @Valid CreateAtribuicaoDTO createAtribuicaoDTO) {
        return new ResponseEntity<>(atribuicaoService.createAtribuicao(createAtribuicaoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AtribuicaoDTO> updateAtribuicao(@RequestBody @Valid UpdateAtribuicaoDTO updateAtribuicaoDTO) {
        return new ResponseEntity<>(atribuicaoService.updateAtribuicao(updateAtribuicaoDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{idAtribuicao}")
    public ResponseEntity<Void> deleteAtribuicao(@PathVariable String idAtribuicao) {
        atribuicaoService.deleteAtribuicao(idAtribuicao);
        return ResponseEntity.noContent().build();
    }

}
