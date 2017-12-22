/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.controller;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO.SolicitacaoDAO;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Solicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitacaoControll 
{
    @Autowired
    SolicitacaoDAO solicitacaoDAO;
  
    @RequestMapping(path="/api/solicitacao", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Solicitacao inserir(@RequestBody Solicitacao solicitacao)
    {
        solicitacao.setId(0);
        Solicitacao solicitacaoSalva = solicitacaoDAO.save(solicitacao);
        return solicitacaoSalva;
    }   
    /*
    @RequestMapping(path = "/usuario/pesquisar/nome", method = RequestMethod.GET)
    public Iterable<Usuario> pesquisaPorNome(
            @RequestParam(required = false) String igual,
            @RequestParam(required = false) String contem) {
        if(igual!=null){
            return usuarioDAO.findByNome(igual);
        } else {
            return usuarioDAO.findByNomeContainingOrderByNome(contem);
        }
    }
    
    @RequestMapping(path = "/produtos/pesquisar/cpf", method = RequestMethod.GET)
    public Iterable<Usuario> pesquisaPorMarcas(@RequestParam(required = false) String igual) {
            return usuarioDAO.findByCpf(igual);
    }
    */
    @RequestMapping(path="/api/solicitacao/{id}", method = RequestMethod.GET)
    public Solicitacao recuperar(@PathVariable int id)
    {
        return solicitacaoDAO.findOne(id); 
    }
    /*
    @RequestMapping(path="/usuario/pesquisar/email", method = RequestMethod.GET)
    public Iterable<Usuario> pesquisarPorEmail(@RequestParam String email)
    {
        return usuarioDAO.findByEmailContainingOrderByNome(email);
    }*/
    
    @RequestMapping(path = "/api/solicitacao", method = RequestMethod.GET)
    public Iterable<Solicitacao> listar(@RequestParam(required = false, defaultValue = "0") int page) {
        PageRequest pageRequest = new PageRequest(page, 10); 
        return solicitacaoDAO.findAll(pageRequest);
    }
    
    @RequestMapping(path= "/api/solicitacao/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable int id) 
    {
        if(solicitacaoDAO.exists(id))
        {
            solicitacaoDAO.delete(id);
        }
    }
    
    @PreAuthorize("hasAuthority('entregador') OR hasAuthority('administrador')")
    @RequestMapping(path = "/api/solicitacao/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@PathVariable int id, @RequestBody Solicitacao solicitacao)
    {
        if(solicitacaoDAO.exists(id))
        {
            solicitacao.setId(id);
            solicitacaoDAO.save(solicitacao);
        }
    } 
}
