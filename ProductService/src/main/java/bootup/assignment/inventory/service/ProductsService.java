package bootup.assignment.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bootup.assignment.inventory.config.ExternalPropertyConfig;
import bootup.assignment.inventory.exception.BlockedProductException;
import bootup.assignment.inventory.exception.WebApplicationException;
import bootup.assignment.inventory.model.Products;
import bootup.assignment.inventory.repository.ProductsRepository;

@Service
@CacheConfig(cacheNames={"products"})
@Transactional
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	ExternalPropertyConfig externalPropertyConfig;

	//find product by Product ID
	@Cacheable("products")
	public Products findProductByID(String productId) throws WebApplicationException {
		Products product = productsRepository.findProductsByid(productId);
		if (null == product) {
			throw new WebApplicationException("Product not found for this id ::" + productId);
		} else {
			return product;
		}

	}

	// Create operation
	public Products createProduct(Products products) {
		
		List<Products> blockedProduct = externalPropertyConfig.getBanned(); 
		
		for(Products eachblockedProduct: blockedProduct) {
			
			if(products.getProductcategory().equalsIgnoreCase(eachblockedProduct.getProductcategory())
					&&
					products.getProductname().equalsIgnoreCase(eachblockedProduct.getProductname())){
				throw new BlockedProductException("This Product is not allowed, falls under Blocked Product Category");
				
			}
		}
		
		
		return productsRepository.save(products);
	}

	// Retrieve operation
	public List<Products> getAllProducts() {
		List<Products> productList = productsRepository.findAll();
		if (null == productList) {
			throw new WebApplicationException("No Product found");
		} else {
			return productList;
		}
	}

	// Update Operation
	public Products updateProductByID(Products product) {
		Products availableproduct = findProductByID(product.getId());
		availableproduct.setProductcategory(product.getProductcategory());
		availableproduct.setProductname(product.getProductname());
		availableproduct.setProductPrice(product.getProductPrice());
		return productsRepository.save(availableproduct);
	}

	// Delete Operation
	public void deleteProductByID(String productId) {
		Products availableproduct = findProductByID(productId);

		productsRepository.delete(availableproduct);
	}

}
