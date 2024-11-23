//package com.example.itog2.configs;
//
//import com.example.itog2.models.Employee;
//import com.example.itog2.repositories.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//
//
//    private final EmployeeRepository userRepository;
//
//    @Autowired
//    public WebSecurityConfig(EmployeeRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(8);
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> {
//            Employee user = userRepository.findByEmail(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("Такой пользователь не существует");
//            }
//
//            // Преобразуем роль в GrantedAuthority
//            GrantedAuthority authority = new SimpleGrantedAuthority(user.getPost().name());
//
//            return new org.springframework.security.core.userdetails.User(
//                    user.getEmail(),
//                    user.getHashPassword(),
//                    true,
//                    true,
//                    true,
//                    true,
//                    Collections.singletonList(authority)  // Передаем одну роль в список
//            );
//        }).passwordEncoder(passwordEncoder());
//    }
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(new AntPathRequestMatcher("/login"), new AntPathRequestMatcher("/registration")).permitAll() // Разрешаем доступ к /login и /registration всем
//                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
//                )
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .csrf().disable()
//                .cors().disable();
//
//        return http.build();
//    }
//}
//
