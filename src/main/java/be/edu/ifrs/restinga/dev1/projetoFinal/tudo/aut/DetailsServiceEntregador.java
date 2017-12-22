/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.aut;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO.EntregadorDAO;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Entregador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetailsServiceEntregador implements UserDetailsService {
    @Autowired
    EntregadorDAO entregadorDAO;
    @Override
    public UserDetails loadUserByUsername(String login) 
            throws UsernameNotFoundException {
        Entregador entregador = entregadorDAO.findByLogin(login);
        if (entregador == null) {
            throw new UsernameNotFoundException(login + " não existe!");
        }
        return new EntregadorAut(entregador);
    }
}
