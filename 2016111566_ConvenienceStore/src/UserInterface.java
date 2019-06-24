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
		
		try { //�����Ϳ� ����� ������ ��ü ����
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
		//�޼ҵ� �̵�
	}catch(IOException ioe) {
		ioe.printStackTrace();
	}
		
		while(on){
		System.out.println("\n����ڸ� �������ּ���.");
		System.out.println("1. ������");
		System.out.println("2. ������");
		System.out.println("3. ��   ��");

		String menu = scan.next();//3���� ��� �� 1�� �Է�

		switch (menu) {
		case "1"://������ �޴�
			System.out.println("\n������ �޴��Դϴ�.");
			System.out.println("�޴��� �������ּ���.");
			System.out.println("1. ��ǰ ����");
			System.out.println("2. ��       ��");
			String con_result = scan.next();
			switch (con_result) {
			case "1"://��ǰ ����
				manage.resetGoods();
				System.out.println("--------��ǰ ���--------");
				manage.showGoodsList(file);
				System.out.print("�����Ͻ� ��ǰ�� ī�װ��� �Է��ϼ���: ");
				category = scan.next();
				try {
					manage.findGoods(category);
				} catch (Exception e) {
					System.out.println("�ش� ī�װ��� �������� �ʽ��ϴ�.");
					break;
				}
				System.out.print("�����Ͻ� ��ǰ�� �̸��� �Է��ϼ���: ");
				product_name = scan.next();
				System.out.print("�����Ͻ� ��ǰ�� ������ �Է��ϼ���: ");
				product_stock = scan.nextInt();
				scan.nextLine();
				input_index=manage.findGoodsIndex(product_name);
				if(input_index>-1) {
					System.out.println("�����ؾ� �ϴ� �� �ݾ��� "+
							manage.sellEstimate(input_index,product_stock)+
							"�� �Դϴ�.");
							System.out.print("�����Ͻðڽ��ϱ�?[y/n]: ");
							switch(scan.next()) {
							case "y":
								manage.sell(input_index,product_name,product_stock,file,sales);
								break;
							case "n":
								System.out.println("���Ű� ��ҵǾ����ϴ�.");
								break;
							default:
								System.out.println("�Է��� �߸� �Ǿ����ϴ�.");
								break;
							}
				} else {
					System.out.println("�ش� ��ǰ�� �������� �ʽ��ϴ�.");
					break;
				}
				break;
			case "2"://����
				System.out.println("�ý����� �����մϴ�.");
				break;
			default:
				System.out.println("�Է��� �߸� �Ǿ����ϴ�.");
				break;
			}
			break;
		case "2"://������ �޴�
			System.out.println("\n������ �޴��Դϴ�.");
			System.out.println("�޴��� �������ּ���.");
			System.out.println("1. ��ǰ �߰�");
			System.out.println("2. ��ǰ ����");
			System.out.println("3. ��ǰ ��ȸ");
			System.out.println("4. ��       ��");
			String ad_result = scan.next();
			switch (ad_result) {
			case "1"://��ǰ �߰�
				System.out.print("��ǰ�� ī�װ��� �Է��ϼ���: ");
				category = scan.next();

				System.out.print("��ǰ�� �̸��� �Է��ϼ���: ");
				product_name = scan.next();
				
				System.out.print("��ǰ�� ������ �Է��ϼ���: ");
				product_price = scan.nextInt();
				scan.nextLine();
				
				System.out.print("��ǰ�� ��� �Է��ϼ���: ");
				product_stock = scan.nextInt();
				scan.nextLine();
				
				goods = new Goods(category, product_name, product_price, product_stock);
				manage.insertGoods(goods,file);//�޼ҵ� �̵�
				break;
			case "2"://��ǰ ����
				manage.resetGoods();
				System.out.println("--------��ǰ ���--------");
				manage.showGoodsList(file);
				System.out.print("�����Ͻ� ��ǰ�� ī�װ��� �Է��ϼ���: ");
				category = scan.next();
				try {
					manage.findGoods(category);
				} catch (Exception e) {
					System.out.println("�ش� ī�װ��� �������� �ʽ��ϴ�.");
					break;
				}
				System.out.print("�����Ͻ� ��ǰ�� �̸��� �Է��ϼ���: ");
				product_name = scan.next();
				manage.deleteGoods(product_name,file);
				break;
			case "3"://���(���� �Է��� ��ǰ ����Ʈ ��ü ���)
				manage.resetGoods();
				System.out.println("--------��ǰ ���--------");
				manage.showGoodsList(file);
				break;
			case "4"://����
				System.out.println("�ý����� �����մϴ�.");
				break;
			default:
				System.out.println("�Է��� �߸� �Ǿ����ϴ�.");
				break;
			}
			break;
		case "3"://����
			System.out.println("�ý����� �����մϴ�.");
			on=false;
			break;
		default:
			System.out.println("�Է��� �߸� �Ǿ����ϴ�.");
			break;
		}
		}
	}
}
