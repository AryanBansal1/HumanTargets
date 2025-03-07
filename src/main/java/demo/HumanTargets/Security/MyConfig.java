package demo.HumanTargets.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
                                                       .requestMatchers("/","/html/index.html","/css/**","/img/**","/js/**","/lib/**","/scss/**","/html/login.html","/html/register.html","/register_process").permitAll()
                                                       .requestMatchers("/admin/**").hasRole("Admin")
                                                        .anyRequest().authenticated())
                                                        
                                                      
                .formLogin(form -> form
                    .loginPage("/html/login.html")
                    .loginProcessingUrl("/perform_login")
                    .successHandler(customSuccessHandler)
                    .failureUrl("/html/404.html"))
                    
                //.httpBasic(Customizer.withDefaults())
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
