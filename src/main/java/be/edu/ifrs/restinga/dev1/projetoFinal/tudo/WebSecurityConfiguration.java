/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.aut.DetailsServiceUsuario;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.controller.UsuarioControll;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.aut.DetailsServiceEntregador;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.controller.EntregadorControll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

/**
 *
 * @author jezer
 */
@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    DetailsServiceUsuario detailsServiceUsuario;
    @Autowired
    DetailsServiceEntregador detailsServiceEntregador;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsServiceUsuario)
                .passwordEncoder(UsuarioControll.PASSWORD_ENCODER);
        auth.userDetailsService(detailsServiceEntregador)
                .passwordEncoder(EntregadorControll.PASSWORD_ENCODER);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(HttpMethod.POST,"/api/usuarios/");
    web.ignoring().antMatchers(HttpMethod.POST,"/api/usuarios/**");
    web.ignoring().antMatchers(HttpMethod.GET,"/api/usuarios/login");
    //web.ignoring().antMatchers(HttpMethod.POST,"/api/imgs/**");
}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and().httpBasic().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();


    }
}