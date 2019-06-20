public class Management {
	private int count=0;
	private Goods[] goodsList = new Goods[100];
	private int sales=0;
	
	public void insertGoods(Goods goods) {
		goodsList[count]=goods;
		count++;
	}
	
	public void deleteGoods(int index) {
		goodsList[index]=null;
	}
	
	public int findGoodsIndex(String product_name) {
		int index=0;
		for(int i=0; i<goodsList.length; i++) {
			if(goodsList[i] != null && goodsList[i].getProduct_name().equals(product_name)) {
				index=i;
			}
		}
		return index;
	}
	
	public void findGoods(String category) {
		for(int i=0; i<goodsList.length; i++) {
			if(goodsList[i] != null && goodsList[i].getCategory().equals(category)) {
				System.out.println(goodsList[i].getProduct_name());
			}
		}
	}
	
	public void getGoodsList() {
		for(int i=0; i<goodsList.length; i++) {
			if(goodsList[i] != null) {
				System.out.println("상품번호["+i+"]");
				System.out.println(goodsList[i].getCategory()+","+
						goodsList[i].getProduct_name()+","+
						goodsList[i].getProduct_price()+","+
						goodsList[i].getProduct_stock());
			}
		}
	}
	
	public int sellEstimate(int index, int sellCount) {
		int pay=goodsList[index].getProduct_price()*sellCount;
		return pay; //find로 찾은 index 물품의 가격*sellcount = 지불해야하는돈
	}
	
	public int sell(int index, int sellCount) {
		// 정상적으로 구매가 되면, 재고가 감소되고, 누적 총계(매출)가 증가되어야 합니다.
		int pay=goodsList[index].getProduct_price()*sellCount;
		if(goodsList[index].getProduct_stock()>=sellCount) { //재고 있으면
			goodsList[index].setProduct_stock(goodsList[index].getProduct_stock()-sellCount);
			sales += pay;
		}else { //재고 없으면
			System.out.println("재고가 없습니다.");
		}
		return pay;//실제 판 가격
	}
}
