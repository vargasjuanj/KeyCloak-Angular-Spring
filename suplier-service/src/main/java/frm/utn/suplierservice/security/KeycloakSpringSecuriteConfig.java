package frm.utn.suplierservice.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class KeycloakSpringSecuriteConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider()); //Spring Security delega la administracion de usuarios a keycloak
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        super.configure(http);
        http.authorizeRequests().antMatchers("/api/v1/ecom/suppliers/**").hasAuthority("MANAGER"); //Solo podran acceder los usuarios autorizados pero con el rol "MANAGER" creado en keycloak
    }
}