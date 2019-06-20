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
			System.out.println("2. ��ǰ ȯ��");
			System.out.println("3. ��       ��");
			String con_result = scan.next();
			switch (con_result) {
			case "1"://��ǰ ����
				System.out.println("--------��ǰ ���--------");
				manage.getGoodsList();
				System.out.print("�����Ͻ� ��ǰ�� ī�װ��� �Է��ϼ���: ");
				category = scan.next();
				manage.findGoods(category);
				System.out.print("�����Ͻ� ��ǰ�� �̸��� �Է��ϼ���: ");
				product_name = scan.next();
				System.out.print("�����Ͻ� ��ǰ�� ������ �Է��ϼ���: ");
				product_stock = scan.nextInt();
				scan.nextLine();
				System.out.println("�����ؾ� �ϴ� �� �ݾ��� "+
				manage.sellEstimate(manage.findGoodsIndex(product_name),product_stock)+
				"�� �Դϴ�.");
				System.out.print("�����Ͻðڽ��ϱ�?[y/n]: ");
				switch(scan.next()) {
				case "y":
					manage.sell(manage.findGoodsIndex(product_name),product_stock);
					break;
				case "n":
					System.out.println("���Ű� ��ҵǾ����ϴ�.");
					break;
				default:
					System.out.println("�Է��� �߸� �Ǿ����ϴ�.");
					break;
				}
				break;
			case "2"://��ǰ ȯ��
				System.out.println("��ǰ�� ȯ���մϴ�.");
				break;
			case "3"://����
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
				
				Goods goods = new Goods(category, product_name, product_price, product_stock);
				manage.insertGoods(goods);//�޼ҵ� �̵�
				break;
			case "2"://��ǰ ����
				System.out.print("��ǰ�� ī�װ��� �Է��ϼ���: ");
				category = scan.next();
				manage.findGoods(category);
				System.out.print("�����Ͻ� ��ǰ�� �̸��� �Է��ϼ���: ");
				product_name = scan.next();
				manage.deleteGoods(manage.findGoodsIndex(product_name));
				break;
			case "3"://���(���� �Է��� ��ǰ ����Ʈ ��ü ���)
				System.out.println("--------��ǰ ���--------");
				manage.getGoodsList();
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
