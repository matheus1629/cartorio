package com.escriba.cartorio.controller;

import com.escriba.cartorio.Service.CartorioService;
import com.escriba.cartorio.dto.cartorio.CartorioCompletoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cartorio", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class CartorioController {

    private final CartorioService cartorioService;

    @GetMapping("/{idCartorio}")
    public ResponseEntity<CartorioCompletoDTO> getById(@PathVariable("idCartorio") Integer idCartorio) {
        return new ResponseEntity<>(cartorioService.getCartorioById(idCartorio), HttpStatus.OK);
    }
}