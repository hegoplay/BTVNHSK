package qlSPham;
import java.util.Objects;
public class Product {
	private String id,productName, manufacture,description;
	private Supplier suplier;
	private double price;
	public Product(String id, String productName, String manufacture, String description, Supplier suplier, double price){
		super(); 
		this.setId(id);
		this.setProductName(productName);
		this.setManufacture(manufacture);
		this.setDescription(description);
		this.setSuplier(suplier);
		this.setPrice(price);
	}
	@Override
	public int hashCode(){
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	public String getManufacture(){
		return manufacture;
	}
	public void setManufacture(String manufacture){
		this.manufacture = manufacture;
	}
	public Supplier getSuplier(){
		return suplier;
	}
	public void setSuplier(Supplier suplier){
		this.suplier = suplier;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
}