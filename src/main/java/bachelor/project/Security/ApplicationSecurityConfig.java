package bachelor.project.Security;


import bachelor.project.Auth.ApplicationUserService;
//import bachelor.project.Security.Firewall.StrictHttpFirewall;
import bachelor.project.Filters.CsrfLoggerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import static bachelor.project.Security.SupplyChainUserRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;


    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{

       //Adds Content Security Policy
//        http.headers()
//                .contentSecurityPolicy("default-src 'self'");

//        Allows HTTPS requests only
//       http
//               .headers().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000);

        http

                //Activating Csrf tokens
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
               .csrf().disable()

                //Establishing an HTTPS request
//                .requiresChannel(channel -> channel.anyRequest().requiresSecure())

                .authorizeRequests()
                .antMatchers("/PandG").hasRole(ADMINS.name())
                .anyRequest()
                .authenticated()
                .and()
              .httpBasic()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/homepage",true)
//                .and()
//                .logout()
//                  .logoutUrl("/logout")
////                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
//                  .clearAuthentication(true)
//                  .invalidateHttpSession(true)
//                  .deleteCookies("JSESSIONID")
//                  .logoutSuccessUrl("/login")
        ;
//       http.addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class);
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
////        return super.userDetailsService();
//        UserDetails Soudi = User.builder()
//                .username("Soudi")
//                .password(passwordEncoder.encode("password"))
////                .roles(RETAILERS.name())
//                .authorities(RETAILERS.getGrantedAuthorities())
//                .build();
//
//        UserDetails Admin1 = User.builder()
//                .username("Admin")
//                .password(passwordEncoder.encode("password"))
////                .roles(ADMINS.name())
//                .authorities(ADMINS.getGrantedAuthorities())
//                .build();
//        return new InMemoryUserDetailsManager(Soudi, Admin1);
//
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
