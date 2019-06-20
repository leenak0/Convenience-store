import java.util.Scanner;

public class UserInterface {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Management manage=new Management();
		boolean on = true;
		int insert_num;
		String category;
		String product_name;
		int product_price;
		int product_stock;
		
		while(on){
		System.out.println("\n사용자를 선택해주세요.");
		System.out.println("1. 구매자");
		System.out.println("2. 관리자");
		System.out.println("3. 종   료");

		String menu = scan.next();//3개의 기능 중 1개 입력

		switch (menu) {
		case "1"://구매자 메뉴
			System.out.println("\n구매자 메뉴입니다.");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1. 상품 구매");
			System.out.println("2. 상품 환불");
			System.out.println("3. 종       료");
			String con_result = scan.next();
			switch (con_result) {
			case "1"://상품 구매
				System.out.println("--------상품 목록--------");
				manage.getGoodsList();
				System.out.print("구매하실 상품의 카테고리를 입력하세요: ");
				category = scan.next();
				manage.findGoods(category);
				System.out.print("구매하실 상품의 이름을 입력하세요: ");
				product_name = scan.next();
				System.out.print("구매하실 상품의 수량을 입력하세요: ");
				product_stock = scan.nextInt();
				scan.nextLine();
				System.out.println("지불해야 하는 총 금액은 "+
				manage.sellEstimate(manage.findGoodsIndex(product_name),product_stock)+
				"원 입니다.");
				System.out.print("구매하시겠습니까?[y/n]: ");
				switch(scan.next()) {
				case "y":
					manage.sell(manage.findGoodsIndex(product_name),product_stock);
					break;
				case "n":
					System.out.println("구매가 취소되었습니다.");
					break;
				default:
					System.out.println("입력이 잘못 되었습니다.");
					break;
				}
				break;
			case "2"://상품 환불
				System.out.println("상품을 환불합니다.");
				break;
			case "3"://종료
				System.out.println("시스템을 종료합니다.");
				break;
			default:
				System.out.println("입력이 잘못 되었습니다.");
				break;
			}
			break;
		case "2"://관리자 메뉴
			System.out.println("\n관리자 메뉴입니다.");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1. 상품 추가");
			System.out.println("2. 상품 삭제");
			System.out.println("3. 상품 조회");
			System.out.println("4. 종       료");
			String ad_result = scan.next();
			switch (ad_result) {
			case "1"://상품 추가

				System.out.print("상품의 카테고리를 입력하세요: ");
				category = scan.next();

				System.out.print("상품의 이름을 입력하세요: ");
				product_name = scan.next();
				
				System.out.print("상품의 가격을 입력하세요: ");
				product_price = scan.nextInt();
				scan.nextLine();
				
				System.out.print("상품의 재고를 입력하세요: ");
				product_stock = scan.nextInt();
				scan.nextLine();
				
				Goods goods = new Goods(category, product_name, product_price, product_stock);
				manage.insertGoods(goods);//메소드 이동
				break;
			case "2"://상품 삭제
				System.out.print("상품의 카테고리를 입력하세요: ");
				category = scan.next();
				manage.findGoods(category);
				System.out.print("삭제하실 상품의 이름을 입력하세요: ");
				product_name = scan.next();
				manage.deleteGoods(manage.findGoodsIndex(product_name));
				break;
			case "3"://출력(새로 입력한 상품 리스트 전체 출력)
				System.out.println("--------상품 목록--------");
				manage.getGoodsList();
				break;
			case "4"://종료
				System.out.println("시스템을 종료합니다.");
				break;
			default:
				System.out.println("입력이 잘못 되었습니다.");
				break;
			}
			break;
		case "3"://종료
			System.out.println("시스템을 종료합니다.");
			on=false;
			break;
		default:
			System.out.println("입력이 잘못 되었습니다.");
			break;
		}
		}
	}
}
