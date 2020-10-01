package bootup.assignment.inventory.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Products extends BaseModel{
	
	
	private String productname;
	private String productcategory;
	private int productPrice;
	
	public Products(String productname, String productcategory, int productPrice) {
		this.productname = productname;
		this.productcategory = productcategory;
		this.productPrice = productPrice;
	}


	
	
	
	

}
