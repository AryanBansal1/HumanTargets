package demo.HumanTargets.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {

    @Autowired
    MyUserDetailservice myUserDetailservice;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security,CustomSuccessHandler customSuccessHandler)throws Exception{
        return security
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                                                       .requestMatchers("/","/index.html","/css/**","/img/**","/js/**","/lib/**","/scss/**","/login.html","/register.html","/register_process","/404.html","/about.html","/causes.html","/team.html","/contact.html","/contactus","/api/payment/**","/success.html","/cancel.html","/NewdoneeRequest","/donor_form").permitAll()
                                                       .requestMatchers("/admin/**","/admin.html").hasRole("Admin")
                                                        .anyRequest().authenticated())
                                                        
                                                      
                .formLogin(form -> form
                    .loginPage("/login.html")
                    .loginProcessingUrl("/perform_login")
                    .successHandler(customSuccessHandler)
                    .failureUrl("/404.html"))
                .exceptionHandling(exception -> exception.accessDeniedPage("/404.html"))
               // .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailservice);
       daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        return daoAuthenticationProvider;
    }
}
