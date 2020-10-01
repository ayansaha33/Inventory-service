package bootup.assignment.inventory.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonInclude;

import bootup.assignment.inventory.util.ProductIdGenerator;

@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @GenericGenerator(
        name = "product_seq", 
        strategy = "bootup.assignment.inventory.util.ProductIdGenerator", 
        parameters = {
            @Parameter(name = ProductIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = ProductIdGenerator.VALUE_PREFIX_PARAMETER, value = "Product_"),
            @Parameter(name = ProductIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
	private String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseModel other = (BaseModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
