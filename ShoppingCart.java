package shoppingcart.domain;

import java.util.ArrayList;
import java.util.List;

import shoppingcart.test.MockListner;

public class ShoppingCart {
	private List<Product> products=new ArrayList<Product>();
	private List<ShoppingCartListner> listners=new ArrayList<ShoppingCartListner>();
	public int total() {
		int total=0;
		for (Product p:products)
			total+=p.getPrice(); 
		return total;
	}
	public void addProduct(Product product) {
		this.products.add(product);
		for (ShoppingCartListner listner:listners)
			try {
				listner.notifyProduct(product.getName(),product.getPrice());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			}
	}
	public void addListner(ShoppingCartListner listner) {
		this.listners.add(listner);
	}
}
