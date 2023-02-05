package com.teste.backend.api.attornatus.resource;

import com.teste.backend.api.attornatus.models.Endereco;
import com.teste.backend.api.attornatus.models.Pessoa;
import com.teste.backend.api.attornatus.service.pessoaService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class pessoaResource {

    @Autowired
    private pessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> listarPessoas(){
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa buscarPorId(@PathVariable("id")Long id){
        return pessoaService.buscarPorId(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não foi encontrada !."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPessoa(@PathVariable("id") Long id){
        pessoaService.buscarPorId(id)
                .map(pessoa -> {
                    pessoaService.deletarPessoa(pessoa.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi posspivel encontrar encontrar a pessoa"));
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarPessoa(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
        pessoaService.buscarPorId(id).map(pessoaBase -> {
            verificarPessoa(pessoaBase, pessoa);
            pessoaService.salvar(pessoa);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível editar"));
    }

    public Pessoa verificarPessoa(Pessoa pessoaBase, Pessoa pessoaNova) {
        pessoaNova.setId(pessoaBase.getId());

        int count = 0;
        for (Endereco endereco : pessoaNova.getEndereco()) {
            pessoaNova.getEndereco().get(count).setId(pessoaBase.getEndereco().get(count).getId());
            count++;
        }
        return pessoaNova;
    }



}
