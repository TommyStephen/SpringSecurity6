package study;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import study.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity

public class SpringSecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.cors(Customizer.withDefaults())/*withDeafults means that a bean by the
		name of corsConfigurationSource is going to be used
		*/
		.csrf(AbstractHttpConfigurer::disable)
		
		.authorizeHttpRequests((authorize) -> {
			authorize.requestMatchers("angular/signup", "angular/login", "angular/findAllUsers")
			.permitAll()
			.requestMatchers("angular/deleteUser").permitAll();
//			.anyRequest().authenticated();
		})
		.httpBasic(Customizer.withDefaults());
		return http.build();

	}
	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration(); 
			config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
			config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE"));
			config.setAllowedHeaders(List.of("Authorization"));
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", config);
			return source;	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
//	@Bean
//	FilterRegistrationBean<CorsFilter> corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		//this allows the backend to retreive the headers which contains the 
//		//authentication information
//		config.setAllowCredentials(true);
//		//url of the front end
//		config.addAllowedOrigin("http://localhost:4200");
//		//typical headers my application must accept
//		config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION,
//				HttpHeaders.CONTENT_TYPE,
//				HttpHeaders.ACCEPT));
//		config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),
//				HttpMethod.POST.name(),
//				HttpMethod.PUT.name(),
//				HttpMethod.DELETE.name()));
//		config.setMaxAge(3600L);
//		//apply the configuration to all the roots
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean<CorsFilter> bean = 
//				new FilterRegistrationBean<CorsFilter>(new CorsFilter((CorsConfigurationSource) source));
//		bean.setOrder(-102);
//		return bean;
//		
//	}
}
