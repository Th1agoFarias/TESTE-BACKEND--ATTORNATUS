package com.teste.backend.api.attornatus.service;


import com.teste.backend.api.attornatus.models.Pessoa;
import com.teste.backend.api.attornatus.repositories.pessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class pessoaService {
    
  @Autowired
  private pessoaRepository pessoaRepository;

  public Pessoa salvar(Pessoa pessoa){
      return pessoaRepository.save(pessoa);
  }

  public List<Pessoa> listarPessoas(){
      return pessoaRepository.findAll();
  }

  public Optional<Pessoa> buscarPorId(Long id){
      return pessoaRepository.findById(id.longValue());
  }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id.longValue());
    }

  
}
