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
				System.out.println("��ǰ��ȣ["+i+"]");
				System.out.println(goodsList[i].getCategory()+","+
						goodsList[i].getProduct_name()+","+
						goodsList[i].getProduct_price()+","+
						goodsList[i].getProduct_stock());
			}
		}
	}
	
	public int sellEstimate(int index, int sellCount) {
		int pay=goodsList[index].getProduct_price()*sellCount;
		return pay; //find�� ã�� index ��ǰ�� ����*sellcount = �����ؾ��ϴµ�
	}
	
	public int sell(int index, int sellCount) {
		// ���������� ���Ű� �Ǹ�, ��� ���ҵǰ�, ���� �Ѱ�(����)�� �����Ǿ�� �մϴ�.
		int pay=goodsList[index].getProduct_price()*sellCount;
		if(goodsList[index].getProduct_stock()>=sellCount) { //��� ������
			goodsList[index].setProduct_stock(goodsList[index].getProduct_stock()-sellCount);
			sales += pay;
		}else { //��� ������
			System.out.println("��� �����ϴ�.");
		}
		return pay;//���� �� ����
	}
}
