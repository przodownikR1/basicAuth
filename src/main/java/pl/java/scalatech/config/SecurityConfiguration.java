package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.java.scalatech.security.ApiAuthenticationEntryPoint;
import pl.java.scalatech.security.ApiAuthenticationFailureHandler;
import pl.java.scalatech.security.ApiAuthenticationSuccessHandler;

@Configuration
@ComponentScan(basePackages="pl.java.scalatech.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private ApiAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private ApiAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private ApiAuthenticationSuccessHandler authenticationSuccessHandler;


    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web.ignoring().
        antMatchers("/assets/**")
        .antMatchers("/css/**")
        .antMatchers("/js/**")
        .antMatchers("/images/**")
        .antMatchers("/favicon.ico")
        .antMatchers("/webjars/**");
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.httpBasic().realmName("basicAuth").and().requiresChannel().anyRequest().requiresSecure();

        http.authorizeRequests().antMatchers("/api/**").authenticated();
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.formLogin().failureHandler(authenticationFailureHandler);

        // @formatter:off
        /*http.httpBasic()
        .and()
        .csrf().disable().headers().disable()
          .authorizeRequests().antMatchers("/login","/logout","secContext","principal","/health").permitAll()
          .antMatchers("/simple/**").hasAnyRole("USER")
          .antMatchers("/api/admin/**").hasRole("ADMIN")
          .antMatchers("/actuator/**").hasRole("ADMIN")
          .antMatchers("/metrics/**").hasRole("ADMIN")
          .antMatchers("/info/**").hasRole("ADMIN")
          .antMatchers("/health/**").hasRole("ADMIN")
          .antMatchers("/trace/**").hasRole("ADMIN")
          .antMatchers("/dump/**").hasRole("ADMIN")
          .antMatchers("/shutdown/**").hasRole("ADMIN")
          .antMatchers("/beans/**").hasRole("ADMIN")
          .antMatchers("/env/**").hasRole("ADMIN")
          .antMatchers("/autoconfig/**").hasRole("ADMIN")


          .anyRequest().permitAll();*/
          // @formatter:on
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

   @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
     // @formatter:off

        auth.inMemoryAuthentication().withUser("przodownik").password("slawek").roles("USER").and()
                                     .withUser("admin").password("slawek").roles( "ADMIN");



     /*   auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select user_username, role from user_roles where user_username=?");
                        */
    }

     // @formatter:on
    }

}