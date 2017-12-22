/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Entregador;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EntregadorDAO extends 
        PagingAndSortingRepository<Entregador, Integer>{
    public Entregador findByLogin(String login);
    
}


