package com.estudo.service;

import com.estudo.domain.LivroConsumer;
import com.estudo.domain.LivroDTO;
import com.estudo.repository.LivroConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class LivroService {

    private LivroConsumerRepository livroConsumerRepository;

    @Autowired
    public LivroService(LivroConsumerRepository livroConsumerRepository) {
        this.livroConsumerRepository = livroConsumerRepository;
    }

    public void create(LivroDTO livroDto) {
        LivroConsumer livroConsumer = new LivroConsumer();
        livroConsumer.setAutor(livroDto.autor());
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toEpochSecond() * 1000);
        livroConsumer.setDataCriacao(date);
        livroConsumerRepository.save(livroConsumer);
    }

    public List<LivroConsumer> listAll() {
        return livroConsumerRepository.findAll();
    }


}
