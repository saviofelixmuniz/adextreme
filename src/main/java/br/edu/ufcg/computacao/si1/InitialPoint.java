package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.security.JwtTokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InitialPoint {

	@Bean
	public FilterRegistrationBean getJwtFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new JwtTokenFilter());
		frb.addUrlPatterns("/user/*");
		return frb;
	}

	public static void main(String[] args) {
		SpringApplication.run(InitialPoint.class, args);
	}
}
