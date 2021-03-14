package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import com.uniovi.services.RolesService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String User = RolesService.getRoles()[0];
	private static final String Admin = RolesService.getRoles()[1];
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			//Opciones publicas
			.antMatchers("/css/**", "/img/**", "/script/**").permitAll()
			.antMatchers("/", "/signup", "/login/**").permitAll()
			//Opciones de Usuario Registrado
			.antMatchers("/user/profile").hasAnyAuthority(User, Admin)
			//Opciones de Usuario Administrador
			.antMatchers("/user/list").hasAuthority(Admin)
			.antMatchers("/user/delete/**").hasAuthority(Admin)
			//Opciones de Usuario Estándar
			.antMatchers("/offer/post").hasAuthority(User)
			.antMatchers("/offer/buy").hasAuthority(User)
			.antMatchers("/offer/all").hasAuthority(User)
			.antMatchers("/offer/own").hasAuthority(User)
			.antMatchers("/offer/purchased").hasAuthority(User)
			//Por defecto			
			.anyRequest().authenticated()
				.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/user/profile")
			.and()
		.logout()
			.permitAll();
		//TODO Preguntar: Como hacer la pagina de errores
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}