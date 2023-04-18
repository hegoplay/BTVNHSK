package qlSPham;

public class Product {
	private String id,productName, manufacture,description;
	private Suplier suplier;
	private double price;
	public Product(String id, String productName, String manufacture, String description, Suplier suplier, double price) {
		super();
		this.setId(id);
		this.setProductName(productName);
		this.setManufacture(manufacture);
		this.setDescription(description);
		this.setSuplier(suplier);
		this.setPrice(price);
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public Suplier getSuplier() {
		return suplier;
	}
	public void setSuplier(Suplier suplier) {
		this.suplier = suplier;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
