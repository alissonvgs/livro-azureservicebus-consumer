package com.estudo.repository;

import com.estudo.domain.LivroConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroConsumerRepository extends JpaRepository<LivroConsumer, Long> {
}