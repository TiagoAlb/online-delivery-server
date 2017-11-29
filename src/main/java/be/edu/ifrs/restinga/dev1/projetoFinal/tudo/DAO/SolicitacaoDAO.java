/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Usuario.Solicitacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tiago
 */
@Repository
public interface SolicitacaoDAO extends PagingAndSortingRepository<Solicitacao, Integer>
{
    Page<Solicitacao> findById(int id, Pageable page);
    
}
    

