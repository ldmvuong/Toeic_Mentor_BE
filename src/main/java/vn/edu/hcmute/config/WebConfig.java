package vn.edu.hcmute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * --------------------------------------------------------------------------
 * WebConfig
 * --------------------------------------------------------------------------
 * Spring Web MVC–level configuration not tied to security.
 * <p>
 *  • Global CORS policy – allows the React front-end (<a href="http://localhost:8500">...</a>)
 *    to call REST endpoints.
 *  • Excludes static and documentation resources from Spring Security.
 * <p>
 * Extend this class to register message converters, locale resolvers, or
 * additional MVC customisations.
 */
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("**")
                        .allowedOrigins("http://localhost:8500")
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                        .allowedHeaders("*") // Allowed request headers
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (WebSecurity web) -> web.ignoring().requestMatchers(
                "/actuator/**",
                "/v3/**",
                "/webjars/**",
                "/swagger-ui*/**",
                "/favicon.ico"
        );
    }
}
