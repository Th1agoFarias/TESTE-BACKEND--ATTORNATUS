package com.teste.backend.api.attornatus.repositories;

import com.teste.backend.api.attornatus.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pessoaRepository extends JpaRepository <Pessoa, Long> {
}
