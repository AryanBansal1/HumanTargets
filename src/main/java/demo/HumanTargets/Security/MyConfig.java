package demo.HumanTargets.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception{
        return security
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                                                        .requestMatchers("null").permitAll()
                                                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())

                .build();
    }
}
