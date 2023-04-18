package qlSPham;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ManageProduct {
	private static String fileName = "21086121_PhamTheManh\\qlSPham\\Products.xml";
	
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document doc;
	public ManageProduct() {
		// TODO Auto-generated constructor stub
		
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse(fileName);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addProduct(Product p) {
		Element root = doc.getDocumentElement();
		
		
		Element nodeP = doc.createElement("product");
		nodeP.setAttribute("id", p.getId());
		root.appendChild(nodeP);
		
		Node nodeName = doc.createElement("productName");
		nodeName.setTextContent(p.getProductName());
		nodeP.appendChild(nodeName);
		
		Node nodeDes = doc.createElement("description");
		nodeDes.setTextContent(p.getDescription());
		nodeP.appendChild(nodeDes);
		

		
		Element nodeSup = doc.createElement("suplier");
		nodeP.appendChild(nodeSup);
		Node nodeSupName = doc.createElement("suplierName");
		nodeSupName.setTextContent(p.getSuplier().getSuplierName());
		nodeSup.appendChild(nodeSupName);
		
		Node nodeSupCountry = doc.createElement("country");
		nodeSupCountry.setTextContent(p.getSuplier().getCountry());
		nodeSup.appendChild(nodeSupCountry);
		
		Node nodeWebsite = doc.createElement("website");
		nodeWebsite.setTextContent(p.getSuplier().getWebsite());
		nodeSup.appendChild(nodeWebsite);
		
		Node nodePrice = doc.createElement("price");
		nodePrice.setTextContent(p.getPrice()+"");
		nodeP.appendChild(nodePrice);
		
	}
	public void deleteProduct(String pid) {
		Element root = doc.getDocumentElement();
		NodeList list = root.getElementsByTagName("product");
		for (int i = 0 ; i < list.getLength();i++) {
			Element product = (Element)list.item(i);
			String id = product.getAttribute("id");
			if (pid.equalsIgnoreCase(id)) {
				product.getParentNode().removeChild(product);
				break;
			}
		}
		System.out.println("Failed");
	}
	
	public void updatePrice(String pid, double price) {
		Element root = doc.getDocumentElement();
		NodeList list = root.getElementsByTagName("product");
		for (int i = 0 ; i < list.getLength();i++) {
			Element product = (Element)list.item(i);
			String id = product.getAttribute("id");
			if (pid.equalsIgnoreCase(id)) {
				Node nodePrice = product.getElementsByTagName("price").item(0);
				nodePrice.setTextContent(price + "");
				break;
			}
		}
		System.out.println("Failed");
	}
	public void writeXMLFile() {
		TransformerFactory factory = null;
		Transformer transformer = null;
		
		try {
			factory = TransformerFactory.newInstance();
			transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(fileName));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printAll() {
		TransformerFactory factory = null;
		Transformer transformer = null;
		
		try {
			factory = TransformerFactory.newInstance();
			transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(System.out));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Product> getAllProducts(){
		ArrayList<Product> list = new ArrayList<Product>();
		Element root = doc.getDocumentElement();
		NodeList productList = root.getElementsByTagName("product");
		for (int i = 0 ; i < productList.getLength();i++) {
			Element nodeProduct = (Element)productList.item(i);
			String id = nodeProduct.getAttribute("id");
			String name = nodeProduct.getElementsByTagName("productName").item(0).getTextContent();
			String manufacture = nodeProduct.getElementsByTagName("manufacture").item(0).getTextContent();
			String description = nodeProduct.getElementsByTagName("description").item(0).getTextContent();
			
			
			Element nodeSup = (Element) nodeProduct.getElementsByTagName("suplier").item(0);
			String supName = nodeSup.getElementsByTagName("suplierName").item(0).getTextContent();
			String country = nodeSup.getElementsByTagName("country").item(0).getTextContent();
			String website = nodeSup.getElementsByTagName("website").item(0).getTextContent();
			Suplier sup = new Suplier(supName, country, website);
			
			double price = Double.parseDouble(nodeProduct.getElementsByTagName("price").item(0).getTextContent());
			Product product = new Product(id, name, manufacture, description, sup, price);
			list.add(product);
			
		}
		return list;
		
	}
}
