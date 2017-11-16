/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Entregador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jezer
 */

@Repository
public interface EntregadorDAO extends PagingAndSortingRepository<Entregador, Integer>
{
    Page<Entregador> findByNome(String nome, Pageable page);
    
    Page<Entregador> findByNomeContainingOrderByNome(String nome, Pageable page);
    
    Page<Entregador> findByCpf(String cpf, Pageable page);
    
    Page<Entregador> findByEmailContainingOrderByNome(String email, Pageable page);
}
