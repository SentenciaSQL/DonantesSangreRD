package com.afriasdev.donacionsangrerd.auth;

import com.afriasdev.donacionsangrerd.auth.filter.JwtAuthenticationFilter;
import com.afriasdev.donacionsangrerd.auth.filter.JwtValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    // Rutas públicas (GET)
    String[] publicGetEndpoints = {
            "/api/tipos-banco", "/api/bancos", "/api/bancos/cercanos",
            "/api/bancos/{page}", "/api/bancos/{id}", "/api/tipos-sangre"
    };

    // Rutas GET solo para ADMIN
    String[] adminGetEndpoints = {
            "/api/usuarios", "/api/usuarios/page/{page}",
            "/api/usuarios/{id}", "/api/personas/{id}"
    };

    // Rutas POST solo para ADMIN
    String[] adminPostEndpoints = { "/api/bancos", "/api/donantes" };

    // Rutas PUT solo para ADMIN
    String[] adminPutEndpoints = {
            "/api/bancos/{id}", "/api/usuarios/{id}",
            "/api/usuarios/**", "/api/personas/**", "/api/donantes/{id}"
    };

    // Rutas DELETE solo para ADMIN
    String[] adminDeleteEndpoints = {
            "/api/bancos/{id}", "/api/usuarios/{id}", "/api/donantes/{id}"
    };

    // Rutas públicas generales (sin importar método)
    String[] publicEndpoints = {
            "/v3/api-docs/**", "/swagger-ui/**",
            "/swagger-ui.html", "/auth/login"
    };

    String[] anyRoleGetEndpoints = {
            "/api/donantes/page/{page}", "/api/donantes/activos", "/donantes/{id}"
    };

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.GET, publicGetEndpoints).permitAll()
                        .requestMatchers(HttpMethod.GET, adminGetEndpoints).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, adminPostEndpoints).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, adminPutEndpoints).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, adminDeleteEndpoints).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, anyRoleGetEndpoints).hasAnyRole("USER", "RECEPTOR", "ADMIN")
                        .requestMatchers(publicEndpoints).permitAll()
                .anyRequest().authenticated())
                .cors(cors -> cors.configurationSource(configurationSource()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean <CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(this.configurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}
