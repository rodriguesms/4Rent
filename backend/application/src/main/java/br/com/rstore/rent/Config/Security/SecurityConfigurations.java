package br.com.rstore.rent.Config.Security;

import br.com.rstore.rent.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TokenService tokenService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Authentication config
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    //Authorization config
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/houses").permitAll()
                .antMatchers(HttpMethod.GET, "/houses/*").permitAll()
                .antMatchers(HttpMethod.GET, "/apartments").permitAll()
                .antMatchers(HttpMethod.GET, "/apartments/*").permitAll()
                .antMatchers(HttpMethod.GET, "/lands").permitAll()
                .antMatchers(HttpMethod.GET, "/lands/*").permitAll()
                .antMatchers(HttpMethod.GET, "/realstates").permitAll()
                .antMatchers(HttpMethod.GET, "/realstates/*").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator").permitAll() // ONLY FOR TESTS WITH ACTUATOR (PRIVATE APP INFO)
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll() // ONLY FOR TESTS WITH ACTUATOR (PRIVATE APP INFO)
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthThroughTokenFilter(tokenService, ownerRepository), UsernamePasswordAuthenticationFilter.class);
                //Stateless auth (Rest principle) => Using token
    }

    //Static resources(js, css, img) config
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

}
