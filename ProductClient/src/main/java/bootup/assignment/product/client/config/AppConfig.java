package bootup.assignment.product.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Configuration File
 */
@Configuration
public class AppConfig {

    /**
     * RestClient Bean definition
     *
     * @return RestTemplate the rest template
     */
    @Bean
    public RestTemplate getRestClient() {
        return new RestTemplate();
    }

}
