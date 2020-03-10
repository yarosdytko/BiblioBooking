package es.code.urjc.BiblioBookingApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //paginas publicas
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/informacion").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        
        //paginas privadas
        http.authorizeRequests().antMatchers("/perfil").hasAnyRole("ADMIN","EMPLEADO","ALUMNO");
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/gestion/**").hasAnyRole("EMPLEADO");
        //http.authorizeRequests().antMatchers("/perfil").hasAnyRole("USER");
        
        //login
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        //http.csrf().disable();
    }
}
