package com.estudo.controller;

import com.estudo.domain.LivroConsumer;
import com.estudo.domain.LivroDTO;
import com.estudo.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/consumer")
public class LivroConsumerController {

    private LivroService livroService;

    @Autowired
    public LivroConsumerController(LivroService livroService) {
        this.livroService = livroService;
    }


    @RequestMapping(value = "/livro", method = RequestMethod.GET)
    public ResponseEntity<List<LivroConsumer>> findAll() {
        List<LivroConsumer> lista =  livroService.listAll();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<LivroConsumer> create(@RequestBody LivroDTO livroDto) {
        livroService.create(livroDto);
        return ResponseEntity.ok().build();
    }

}
