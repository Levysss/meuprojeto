package com.levy.meuprojeto.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.levy.meuprojeto.modelo.Pessoa;
import com.levy.meuprojeto.repositorio.Repositorio;
import com.levy.meuprojeto.servico.Servico;

@RestController
public class Controle{
    
    @Autowired
    private Repositorio acao;
    @Autowired
    private Servico servico;

    @GetMapping("/api")
    public List<Pessoa> selecionar(){
        return acao.findAll();
    }

    @PutMapping("/api")
    public Pessoa editar(@RequestBody Pessoa obj){
        return acao.save(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public void remover(@PathVariable int codigo){
        Pessoa obj = selecionarPeloCodigo(codigo);

        acao.delete(obj);
    }
    @GetMapping("/api/contador")
    public Long contador(){
        return acao.count();
    }
    @GetMapping("/api/ordemNome")
    public List<Pessoa> ordemNome(){
        return acao.findByOrderByNome();
    }
    @GetMapping("/api/ordemNome2")
    public List<Pessoa> ordemNome2(){
        return acao.findByNomeOrderByIdadeDesc("Cris");
    }
    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem(){
        return acao.findByNomeContaining("r");
    }
    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("S");
    }
    @GetMapping("/api/terminaCom")
    public List<Pessoa> teminaCom(){
        return acao.findByNomeEndsWith("s");
    }
    @GetMapping("/api/somarIdades")
    public int somaIdades(){
        return acao.somaidade();
    }
    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(18);
    }
    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/{codigo}")
    public Pessoa selecionarPeloCodigo(@PathVariable int codigo){
        return acao.findByCodigo(codigo); 
    }

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }

    @GetMapping("/")
    public String mensagem(){
        return "Hello Word !";
    }
    @GetMapping("/boasVindas/")
    public String boasVindas(){
        return "Seja bem-vindo(a) ";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome){
        return "Seja bem-vindo(a) "+nome;
    }
}
