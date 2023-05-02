package com.levy.meuprojeto.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.levy.meuprojeto.modelo.Mensagem;
import com.levy.meuprojeto.modelo.Pessoa;
import com.levy.meuprojeto.repositorio.Repositorio;

@Service
public class Servico {
    @Autowired
    private Mensagem mensagem;
    @Autowired
    private Repositorio acao;

    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equals("")){
            mensagem.setMensagem("O nome preciasa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() <0){
            mensagem.setMensagem("Informe Uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }
}
