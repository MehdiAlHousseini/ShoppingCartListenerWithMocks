package shoppingcart.domain;

public class Product {
	private String name;
	private int price;
	private Discount discount=new DiscountWithoutDiscount();
	
	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	public Product(String name,int price,Discount discount) {
		this(name,price);
		setDiscount(discount);
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return discount.apply(price);
	}  
	public void setDiscount(Discount discount) {
		this.discount=discount;
	}
	

	
}
