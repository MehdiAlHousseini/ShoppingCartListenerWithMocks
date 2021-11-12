package shoppingcart.test;

import shoppingcart.domain.Discount;

public class PercentageDiscount implements Discount {
	private int percentage;
	

	public PercentageDiscount(int percentage) {
		this.percentage=percentage;
	}

	@Override
	public int apply(int price) {
		// TODO Auto-generated method stub
		return (price*(100-percentage))/100;
	}

}
