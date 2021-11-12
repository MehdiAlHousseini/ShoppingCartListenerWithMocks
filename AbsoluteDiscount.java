package shoppingcart.domain;

public class AbsoluteDiscount implements Discount{
	
private int discount;
public AbsoluteDiscount(int value) {
	discount=value;
}

@Override
public int apply(int price) {
	if (discount>price)
		return 0;
	return price -discount;
}
	

}
