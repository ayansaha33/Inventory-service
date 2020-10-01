package bootup.assignment.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

import bootup.assignment.inventory.config.WebPropertyConfig;

@SpringBootApplication
@EnableCaching
@Import({WebPropertyConfig.class})
@EnableConfigurationProperties
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
