package bootup.assignment.product.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Products{
	
	private String id;
	private String productname;
	private String productcategory;
	private int productPrice;
	
	public Products(String productname, String productcategory, int productPrice) {
		this.productname = productname;
		this.productcategory = productcategory;
		this.productPrice = productPrice;
	}


	
	
	
	

}
