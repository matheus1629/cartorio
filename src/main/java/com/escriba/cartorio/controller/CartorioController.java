package com.escriba.cartorio.controller;

import com.escriba.cartorio.Service.CartorioService;
import com.escriba.cartorio.dto.PageDTO;
import com.escriba.cartorio.dto.cartorio.UpdateCartorioDTO;
import com.escriba.cartorio.dto.cartorio.CartorioDTO;
import com.escriba.cartorio.dto.cartorio.CartorioPaged;
import com.escriba.cartorio.dto.cartorio.CreateCartorioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/cartorio", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class CartorioController {

    private final CartorioService cartorioService;

    @GetMapping("/page/{page}")
    public ResponseEntity<PageDTO<CartorioPaged>> getByPage(@PathVariable("page") Integer page) {
        return new ResponseEntity<>(cartorioService.getPagedCartorios(page), HttpStatus.OK);
    }

    @GetMapping("/data/{idCartorio}")
    public ResponseEntity<CartorioDTO> getById(@PathVariable("idCartorio") Integer idCartorio) {
        return new ResponseEntity<>(cartorioService.getCartorioById(idCartorio), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CartorioDTO> createCartorio(@RequestBody @Valid CreateCartorioDTO createCartorioDTO) {
        return new ResponseEntity<>(cartorioService.createCartorio(createCartorioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CartorioDTO> updateCartorio(@RequestBody @Valid UpdateCartorioDTO updateCartorioDTO) {
        return new ResponseEntity<>(cartorioService.updateCartorio(updateCartorioDTO), HttpStatus.ACCEPTED);
    }

}
