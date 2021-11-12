package shoppingcart.domain;

public class DiscountWithoutDiscount implements Discount {

	@Override
	public int apply(int price) {
		// TODO Auto-generated method stub
		return price;
	}
	
	
}
