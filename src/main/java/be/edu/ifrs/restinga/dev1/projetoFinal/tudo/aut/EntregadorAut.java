/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.aut;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Entregador;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
public class EntregadorAut extends User {
    private Entregador entregador;
    public EntregadorAut(Entregador entregador) {
        super(entregador.getLogin(),
                entregador.getSenha(),
                AuthorityUtils.createAuthorityList(
                        entregador.getPermissoes().toArray(new String[]{})));
        this.entregador=entregador;
    }

    public Entregador getEntregador() {
        return entregador;
    }
    
    
}
