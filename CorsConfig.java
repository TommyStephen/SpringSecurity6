package study;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebMvc

public class CorsConfig implements WebMvcConfigurer {
	
		
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Allow requests from any origin. You can district access from a certain IP here
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
       
        System.out.println(("CORS configured to allow requests from any origin."));
    }
}
