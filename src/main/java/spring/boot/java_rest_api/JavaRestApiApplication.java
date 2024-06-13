package spring.boot.java_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import spring.boot.java_rest_api.config.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class JavaRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaRestApiApplication.class, args);
	}

}
