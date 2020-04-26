package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT")
public class Product
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="CATEGORY")
	@NotEmpty(message = "Please provide a category")
	private String category;
	
	@Column(name="Brand")
	@NotEmpty(message = "Please provide a brand")
	private String brand;
	    
	@Column(name="PRODUCT_NAME")
    @NotEmpty(message = "Please provide a product Name")
    private String productName;
    
    @Column(name="PRICE")
    @NotNull(message = "Please provide a price")
    private Double price;
    
    @Column(name="CURRENCY_CODE")
    @NotEmpty(message = "Please provide a currency code")
    private String currencyCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
