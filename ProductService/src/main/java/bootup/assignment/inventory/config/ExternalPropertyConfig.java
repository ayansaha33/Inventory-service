package bootup.assignment.inventory.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import bootup.assignment.inventory.model.Products;


@Component
@ConfigurationProperties(prefix = "product")
public class ExternalPropertyConfig {

	private List<Products> banned;

	public List<Products> getBanned() {
		return banned;
	}

	public void setBanned(List<Products> banned) {
		this.banned = banned;
	}

	public ExternalPropertyConfig(List<Products> banned) {
		this.banned = banned;
	}


	

	
}
