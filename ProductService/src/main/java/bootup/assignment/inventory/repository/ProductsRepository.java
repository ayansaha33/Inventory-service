package bootup.assignment.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bootup.assignment.inventory.model.Products;


@Repository
public interface ProductsRepository extends JpaRepository<Products, String>{
	
	Products findProductsByid(String productId);
	

}
