package sunpp.its.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//        .anyRequest().authenticated());
//    http.formLogin(Customizer.withDefaults());

    http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest()
        .permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable);
    return http.build();
  }
}
