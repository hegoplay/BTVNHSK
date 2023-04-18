package qlSPham;

public class Supplier {
	private String suplierName, country, website;

	public Supplier(String suplierName, String country, String website) {
		super();
		this.setSuplierName(suplierName);
		this.setCountry(country);
		this.setWebsite(website);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSuplierName() {
		return suplierName;
	}

	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}
	
}
