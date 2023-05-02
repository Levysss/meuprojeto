//pacote
package com.levy.meuprojeto.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
//importa o CrudRepository e a anotação @Repository
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//importa classe pessoa
import com.levy.meuprojeto.modelo.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {
    List<Pessoa> findAll();
    List<Pessoa> findByOrderByNome();
    List<Pessoa> findByNomeOrderByIdadeDesc(String nome);
    List<Pessoa> findByNomeContaining(String termo);
    List<Pessoa> findByNomeStartsWith(String termo);
    List<Pessoa> findByNomeEndsWith(String termo);
    Pessoa findByCodigo(int codigo);

    @Query(value = "SELECT SUM(idade) FROM pessoas", nativeQuery = true)
    int somaidade();
    @Query(value = "SELECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int idade);
}
