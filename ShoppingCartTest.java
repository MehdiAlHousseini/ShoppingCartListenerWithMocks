package shoppingcart.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shoppingcart.domain.AbsoluteDiscount;
import shoppingcart.domain.Product;
import shoppingcart.domain.ShoppingCart;
import shoppingcart.domain.ShoppingCartListner;

class ShoppingCartTest {
	private ShoppingCart sc;
	
	@BeforeEach
	void initializeCart() {
		sc=new ShoppingCart();
	}

	@Test
	void emptyShoppingCart() {
		assertEquals(0,sc.total());
	}	 
	@Test
	void addOneProduct() {
		Product p=new Product("T-shirt",30);
		sc.addProduct(p);
		assertEquals(30,sc.total());
		
	}
	@Test
	void addTwoProduct() {
		sc.addProduct(new Product("T-shirt",30));
		sc.addProduct(new Product("Snickers",20));
		assertEquals(50,sc.total());
		
	}
	@Test
	void notifyListner() {
		MockListner mock=new MockListner();
		sc.addListner(mock);
		assertFalse(mock.receiceNotification());
		sc.addProduct(new Product("T-shirt",30));
		assertTrue(mock.receiceNotification());
		mock.assertLastProductReceived("T-shirt",30);
		
	}
	@Test
	void notifyMoreThanOneListner() {
		MockListner mock1=new MockListner();
		MockListner mock2=new MockListner();

		sc.addListner(mock1);
		sc.addListner(mock2);
		sc.addProduct(new Product("T-shirt",30));
		assertAll(
				()->assertTrue(mock1.receiceNotification()),
				()->assertTrue(mock2.receiceNotification())

				
				);
		
	}
	@Test
	void ListnerThatThrowsAnException() {
		MockListner mock1=new MockListner();
		ShoppingCartListner failedMock =new ShoppingCartListner() {

			@Override
			public void notifyProduct(String name, int price) {
				throw new RuntimeException();
					
				
			}
			
		};
		
		MockListner mock2=new MockListner();

		sc.addListner(mock1);
		sc.addListner(failedMock);
		sc.addListner(mock2);
		sc.addProduct(new Product("T-shirt",30));
		assertAll(
				()->assertTrue(mock1.receiceNotification()),
				()->assertTrue(mock2.receiceNotification())

				
				);
		
	}
	
	@Test
	void productWithAbsoluteDiscount() {
		Product p =new Product("T-shirt",30);
		p.setDiscount(new AbsoluteDiscount(35));
		sc.addProduct(p);
		assertEquals(0,sc.total());
		
	}
	@Test
	void productWithPercentageDiscount() {
		Product p =new Product("T-shirt",30);
		p.setDiscount(new PercentageDiscount(20));
		sc.addProduct(p);
		assertEquals(24,sc.total());
		
	}
	@Test
	void productsWithDifferentKindsOfDiscount() {
		sc.addProduct(new Product("T-shirt",30,new AbsoluteDiscount(10)));
		sc.addProduct(new Product("Snickers",80,new PercentageDiscount(15)));
		sc.addProduct(new Product("Gloves",20));
		assertEquals(108,sc.total());
		
	}
	

}
 
