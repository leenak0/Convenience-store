import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Management manage=new Management();
		boolean on = true;
		
		int input_index;
		String input_category;
		
		String category;
		String product_name;
		int product_price;
		int product_stock;
		Goods goods=null;
		String file="goods.txt";
		String sales="sales.txt";
		
		try { //데이터에 저장된 굿즈의 객체 생성
		BufferedReader bi = new BufferedReader(new FileReader("goods.txt"));
		String s;
		while((s=bi.readLine())!=null) {
			String[]split=s.split("\t");
			category=split[0];
			product_name=split[1];
			product_price=Integer.valueOf(split[2]);
			product_stock=Integer.valueOf(split[3]);
			goods = new Goods(category, product_name, product_price, product_stock);
			manage.makeGoods(goods);
		}
		bi.close();
		//메소드 이동
	}catch(IOException ioe) {
		ioe.printStackTrace();
	}
		
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
			System.out.println("2. 종       료");
			String con_result = scan.next();
			switch (con_result) {
			case "1"://상품 구매
				manage.resetGoods();
				System.out.println("--------상품 목록--------");
				manage.showGoodsList(file);
				System.out.print("구매하실 상품의 카테고리를 입력하세요: ");
				category = scan.next();
				try {
					manage.findGoods(category);
				} catch (Exception e) {
					System.out.println("해당 카테고리가 존재하지 않습니다.");
					break;
				}
				System.out.print("구매하실 상품의 이름을 입력하세요: ");
				product_name = scan.next();
				System.out.print("구매하실 상품의 수량을 입력하세요: ");
				product_stock = scan.nextInt();
				scan.nextLine();
				input_index=manage.findGoodsIndex(product_name);
				if(input_index>-1) {
					System.out.println("지불해야 하는 총 금액은 "+
							manage.sellEstimate(input_index,product_stock)+
							"원 입니다.");
							System.out.print("구매하시겠습니까?[y/n]: ");
							switch(scan.next()) {
							case "y":
								manage.sell(input_index,product_name,product_stock,file,sales);
								break;
							case "n":
								System.out.println("구매가 취소되었습니다.");
								break;
							default:
								System.out.println("입력이 잘못 되었습니다.");
								break;
							}
				} else {
					System.out.println("해당 상품이 존재하지 않습니다.");
					break;
				}
				break;
			case "2"://종료
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
				
				goods = new Goods(category, product_name, product_price, product_stock);
				manage.insertGoods(goods,file);//메소드 이동
				break;
			case "2"://상품 삭제
				manage.resetGoods();
				System.out.println("--------상품 목록--------");
				manage.showGoodsList(file);
				System.out.print("삭제하실 상품의 카테고리를 입력하세요: ");
				category = scan.next();
				try {
					manage.findGoods(category);
				} catch (Exception e) {
					System.out.println("해당 카테고리가 존재하지 않습니다.");
					break;
				}
				System.out.print("삭제하실 상품의 이름을 입력하세요: ");
				product_name = scan.next();
				manage.deleteGoods(product_name,file);
				break;
			case "3"://출력(새로 입력한 상품 리스트 전체 출력)
				manage.resetGoods();
				System.out.println("--------상품 목록--------");
				manage.showGoodsList(file);
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
