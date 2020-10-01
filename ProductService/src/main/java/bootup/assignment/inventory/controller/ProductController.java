package bootup.assignment.inventory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootup.assignment.inventory.model.Products;
import bootup.assignment.inventory.service.ProductsService;

/*
 * 
 * -----------Requirement 1 -----------
 * Allow inventory managers in the company to view/add/update/delete products in the inventory.  
 *   a.)Get list of all products in the inventory --------Line 58
 *   b.)Add a new product ------Line 46
 *   c.)Delete existing product ------ Line 82
 *   d.)Get the price of a given product. Since this end-point is used to check price periodically,
 *    cache the HTTP response for 30 minutes.
 *    Get the price of a given product.
 *    Since this end-point is used to check price periodically,
 *    cache the HTTP response for 30 minutes. ----------Line 62
 *   e.)Update price of an existing product.-------------Line 74
 */
@RestController
@RequestMapping("/api/product/service")
public class ProductController {

	private static final String SPECIAL_PRODUCT = "Product_00001";

	@Autowired
	private ProductsService productService;

	@PostMapping("/createproduct")
	public ResponseEntity<Products> createProduct(@RequestBody Products product) {

		Products products = productService.createProduct(product);

		return new ResponseEntity<>(products, HttpStatus.CREATED);

	}

	@GetMapping("/getAllproduct")
	public ResponseEntity<List<Products>> getAllProduct() {

		List<Products> productList = new ArrayList<Products>();
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);

		productService.getAllProducts().forEach(productList::add);

		return ResponseEntity.ok().cacheControl(cacheControl).body(productList);
	}

	@GetMapping("/getproduct/{productId}")
	public ResponseEntity<Products> getProduct(@PathVariable(required = false) String productId) {

		Products products = null;
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);

		if (productId.equalsIgnoreCase(SPECIAL_PRODUCT)) {
			/*
			 * Requirement 4 --Retrieve product details for a given id, if the id is 1
			 * return a http code of 302, else set the response status as 200 and return the
			 * product details.
			 *
			 */
			products = productService.findProductByID(productId);
			return new ResponseEntity<Products>(products, HttpStatus.FOUND);

		}

		products = productService.findProductByID(productId);

		return ResponseEntity.ok().cacheControl(cacheControl).body(products);
	}

	@PutMapping("/updateproduct")
	public ResponseEntity<Products> updateProduct(@RequestBody Products product) {

		Products products = productService.updateProductByID(product);

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@DeleteMapping("/deleteproduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId) {

		productService.deleteProductByID(productId);
		
		return new ResponseEntity<>("Deleted the Product with ID ::"+productId, HttpStatus.OK);

	}

}
