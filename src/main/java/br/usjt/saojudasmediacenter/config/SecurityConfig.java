package br.usjt.saojudasmediacenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.usjt.saojudasmediacenter.service.UsuarioService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired UsuarioService usuarioService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()			
			.antMatchers("/*/role_admin/**").hasRole("ADMIN")
			.antMatchers("/*/role_estagiario/**", "/role_estagiario/**").hasAnyRole("ADMIN", "ESTAGIARIO")
			.antMatchers("/*/autenticado/**").authenticated()
			.and().formLogin().loginPage("/usuarios/login").permitAll()
			.and().httpBasic()
			.and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
