package net.jdazher.infrastructure.tasks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up web-related configurations in the application.
 *
 * <p>This class implements the {@link WebMvcConfigurer} interface to customize
 * the Spring MVC configuration, specifically for handling CORS settings.</p>
 *
 * <p>It allows cross-origin requests from the specified origins and methods, enabling
 * communication between the frontend and backend services.</p>
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Adds CORS mappings for the application.
     *
     * <p>This method configures the CORS settings for all endpoints by allowing requests
     * from a specific origin, as well as specifying the allowed HTTP methods and headers.</p>
     *
     * @param registry The {@link CorsRegistry} used to define the CORS configuration.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

}
