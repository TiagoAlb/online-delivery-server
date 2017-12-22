/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.controller;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.aut.ForbiddenException;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.aut.EntregadorAut;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO.EntregadorDAO;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Entregador;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tiago
 */

@RestController
@RequestMapping(path = "/api")
public class EntregadorControll {

    public static final PasswordEncoder 
            PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @PreAuthorize("hasAuthority('administrador')")
    @RequestMapping(path = "/entregadores", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Entregador inserir(@AuthenticationPrincipal EntregadorAut entregadorAut, @RequestBody Entregador entregador) {
        entregador.setId(0);
        entregador.setSenha(PASSWORD_ENCODER.encode(entregador.getNovaSenha()));

            ArrayList<String> permissao = new ArrayList<String>();
            permissao.add("entregador");
            entregador.setPermissoes(permissao);
        
        Entregador entregadorSalvo = entregadorDAO.save(entregador);
        return entregadorSalvo;
    } 
    
    @Autowired
    EntregadorDAO entregadorDAO;

    @PreAuthorize("hasAuthority('administrador')")
    @RequestMapping(path = "/entregadores", method = RequestMethod.GET)
    public Iterable<Entregador> listar(@RequestParam(required = false, defaultValue = "0") int pagina) {
        PageRequest pageRequest = new PageRequest(pagina, 5);
        return entregadorDAO.findAll(pageRequest);
    }

    @PreAuthorize("hasAuthority('administrador') OR hasAuthority('entregador')")
    @RequestMapping(path = "/entregadores/{id}", method = RequestMethod.GET)
    public Entregador recuperar(@AuthenticationPrincipal EntregadorAut entregadorAut, @PathVariable int id) {
        if (entregadorAut.getEntregador().getId() == id 
                || entregadorAut.getEntregador().getPermissoes().contains("administrador")) {
            return entregadorDAO.findOne(id);
        } else {
            throw new ForbiddenException("Não é permitido acessar dados de outro usuários");
        }
    }

    @PreAuthorize("hasAuthority('administrador')")
    @RequestMapping(path = "/entregadores/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@PathVariable int id, @RequestBody Entregador entregador) {
        if (entregadorDAO.exists(id)) {
            entregador.setId(id);
            entregadorDAO.save(entregador);
        }
    }

    @PreAuthorize("hasAuthority('administrador')")
    @RequestMapping(path = "/entregadores/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable int id) {
        if (entregadorDAO.exists(id)) {
            entregadorDAO.delete(id);
        }
    }
    
    @RequestMapping(path = "/entregadores/login", method = RequestMethod.GET)
    public Entregador login(@AuthenticationPrincipal EntregadorAut EntregadorAut) {
        return EntregadorAut.getEntregador();
    
    } 

}
