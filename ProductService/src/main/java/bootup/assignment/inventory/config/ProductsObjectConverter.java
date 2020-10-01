package bootup.assignment.inventory.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bootup.assignment.inventory.model.Products;

@Component
@ConfigurationPropertiesBinding
public class ProductsObjectConverter implements Converter<String, Products>{

	@Override
	public Products convert(String source) {
		String[] data = source.split(",");
		return new Products(data[0],data[1],0);
	}

}
