package es.code.urjc.BiblioBooking;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //paginas publicas
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/informacion").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        
        //paginas privadas
        http.authorizeRequests().anyRequest().authenticated();
        
        //login
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("uname");
        http.formLogin().passwordParameter("passwd");
        http.formLogin().defaultSuccessUrl("/perfil");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        // Disable CSRF at the moment
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// User
        auth.inMemoryAuthentication()
                .withUser("ydytko").password("1234").roles("ADMIN");
    }

}
