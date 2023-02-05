package com.teste.backend.api.attornatus.repositories;

import com.teste.backend.api.attornatus.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface enderecoRepository extends JpaRepository <Endereco, Long> {
    public Endereco findByPessoaIdAndEnderecoPrincipal(Long pessoaId, Boolean enderecoPrincipal);
}
