package shoppingcart.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import shoppingcart.domain.ShoppingCartListner;

public class MockListner implements ShoppingCartListner {
	public boolean wasNotified=false;
	private String nameReceived;
	private int priceReceived;
	public boolean receiceNotification() {
		// TODO Auto-generated method stub
		return wasNotified;
	}

	@Override
	public void notifyProduct(String name, int price) {
		wasNotified=true;
		nameReceived=name;
		priceReceived=price;
	}

	public void assertLastProductReceived(String name, int price) {
		assertEquals(name,nameReceived);
		assertEquals(price,priceReceived);

	}

}
