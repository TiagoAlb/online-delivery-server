/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cassi
 */
@Repository
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Integer>
{
    Page<Usuario> findByNome(String nome, Pageable page);
    
    Page<Usuario> findByNomeContainingOrderByNome(String nome, Pageable page);
    
    Page<Usuario> findByCpf(String cpf, Pageable page);
    
    Page<Usuario> findByEmailContainingOrderByNome(String email, Pageable page);
    
}
    

