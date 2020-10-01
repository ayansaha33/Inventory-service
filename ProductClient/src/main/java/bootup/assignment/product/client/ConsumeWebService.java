package bootup.assignment.product.client;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import bootup.assignment.product.client.config.WebPropertyConfig;
import bootup.assignment.product.client.model.Products;


@RestController
@RequestMapping("/api/product/client")
public class ConsumeWebService {
	
	
   public static final String FETCH_URI_FOR_PRODUCTS = "app.fetch.uri.list.of.products";
   public static final String FETCH_URI_FOR_PRODUCTS_BY_ID = "app.fetch.uri.list.of.products.by.id";
	
	
   @Autowired
   RestTemplate restTemplate;
   
   @Autowired
   WebPropertyConfig itsProperties;

   @RequestMapping(value = "/getAllProducts")
   public List<Products> getProductList() {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML));
      HttpEntity <String> entity = new HttpEntity<String>(headers);
      ResponseEntity<List<Products>> responseEntity = restTemplate.exchange(itsProperties.getProperty(FETCH_URI_FOR_PRODUCTS), HttpMethod.GET, entity,new ParameterizedTypeReference<List<Products>>() {});
      List<Products> listOfProducts = responseEntity.getBody();
      return listOfProducts;
      //return restTemplate.exchange(itsProperties.getProperty(FETCH_URI_FOR_PRODUCTS), HttpMethod.GET, entity,);
      //return restTemplate.getForObject(itsProperties.getProperty(FETCH_URI_FOR_PRODUCTS), Products.class);
   }
   
   @RequestMapping(value = "/getproducts/{productId}")
   public ResponseEntity<Products> getProductbyProductID(@PathVariable String productId) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML));
      HttpEntity <String> entity = new HttpEntity<String>(headers);
      
      Map<String,String> params = new HashMap<String,String>();
      params.put("productId", productId);
      
      URI uri = UriComponentsBuilder.fromUriString(itsProperties.getProperty(FETCH_URI_FOR_PRODUCTS_BY_ID))
    	        .buildAndExpand(params)
    	        .toUri();
      
      return restTemplate.exchange(uri , HttpMethod.GET, entity, Products.class);
      //return restTemplate.exchange(itsProperties.getProperty(FETCH_URI_FOR_PRODUCTS), HttpMethod.GET, entity,);
      //return restTemplate.getForObject(itsProperties.getProperty(FETCH_URI_FOR_PRODUCTS), Products.class);
	
   }
}