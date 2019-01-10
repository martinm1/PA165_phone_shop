package cz.muni.fi.pa165.smartphonEShop.mvc.config;

import cz.muni.fi.pa165.smartphonEShop.mvc.security.PersonAuthProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan(basePackages = "cz.muni.fi.pa165.smartphonEShop.mvc.security")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{
    @Inject
    private PersonAuthProvider provider;

    public MySecurityConfig()
    {
        super();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/home")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception
    {
        builder.authenticationProvider(provider);
    }
}
