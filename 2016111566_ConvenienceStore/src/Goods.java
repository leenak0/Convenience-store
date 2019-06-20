public class Goods {
	private String category;//물품 종류
	private String product_name;//제품명
	private int product_num=0;//제품번호
	private int product_price;//가격
	private int product_stock;//재고
	
	Goods(String category, String product_name, int product_price,
			int product_stock){
		this.category=category;
		this.product_name=product_name;
		this.product_price=product_price;
		this.product_stock=product_stock;
		product_num++;
	}
	public String getCategory(){
		return category;
	}
	
	public void setCategory(String category) {
		this.category=category;
	}
	
	public String getProduct_name(){
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name=product_name;
	}
	
	public int getProduct_num(){
		return product_num;
	}
	
	public void setProduct_num(int product_num) {
		this.product_num=product_num;
	}
	
	public int getProduct_price(){
		return product_price;
	}
	
	public void setProduct_price(int product_price) {
		this.product_price=product_price;
	}
	
	public int getProduct_stock(){
		return product_stock;
	}
	
	public void setProduct_stock(int product_stock) {
		this.product_stock=product_stock;
	}
	
	public void addStock(String product_name, int addstock){
		product_stock+=addstock;
		System.out.println(addstock+"개가 입고되었습니다.");
	}
	
	public void subStock(String product_name, int substock) throws Exception{
		if(product_stock>=substock) {
			product_stock-=substock;
		}else {
			throw new Exception("재고가 충분하지 않습니다.");
		}
		
	}
}
