import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Management {
	private int count=0;
	private Goods[] goodsList = new Goods[100];
	private int sales=0;
	
	public void resetGoods() { //�����Ʈ �ʱ�ȭ
		for(int i=0; i<goodsList.length; i++) {
			if(goodsList[i] != null) {
				goodsList[i]=null;
			}
		}
		count=0;
	}
	
	public void makeGoods(Goods goods) { //�����Ʈ �����
		goodsList[count]=goods;
		count++;
	}
	
	public void insertGoods(Goods goods,String file) { //��ǰ�߰�
		goodsList[count]=goods;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			bw.write(goodsList[count].getCategory()+"\t"+
					goodsList[count].getProduct_name()+"\t"+
					goodsList[count].getProduct_price()+"\t"+
					goodsList[count].getProduct_stock());
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		count++;
	}
	
	public void deleteGoods(String product_name,String file) {
		ArrayList<String> line=new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String lines=br.readLine();
				if(lines==null) {
					break;
				}else if(!lines.split("\t")[1].equals(product_name)){
					line.add(lines);
				}
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int i=0;i<line.size();i++) {
				bw.write(line.get(i));
				bw.newLine();
				bw.flush();
			}
			bw.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public int findGoodsIndex(String product_name) {
		int index=-1;
		for(int i=0; i<goodsList.length; i++) {
			if(goodsList[i] != null && goodsList[i].getProduct_name().equals(product_name)) {
				index=i;
			}
		}
		return index;
	}
	
	public void findGoods(String category) throws Exception{
		int index=-1;
		for(int i=0; i<goodsList.length; i++) {
			if(goodsList[i] != null && goodsList[i].getCategory().equals(category)) {
				System.out.println(goodsList[i].getProduct_name());
				index++;
			}
		}
		if(index==-1) {
			throw new Exception();
		}
	}
	
	public void showGoodsList(String file) {
		try { //�����Ϳ� ����� ������ ��ü ����
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			while((s=br.readLine())!=null) {
				String[]split=s.split("\t");
				Goods goods = new Goods(split[0], split[1], Integer.valueOf(split[2]),
						Integer.valueOf(split[3]));
				makeGoods(goods);
			}
			br.close();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
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
	
	public void sell(int index, String product_name, int sellCount, String file, String sales) {
		Boolean isstock=false;
		int pay=goodsList[index].getProduct_price()*sellCount; //�Ѱ���
		ArrayList<String> line=new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String lines=br.readLine();
				if(lines==null) {
					break;
				}else if(lines.split("\t")[1].equals(product_name)){
					if(Integer.parseInt(lines.split("\t")[3])>=sellCount) { //��� ������
						String lines2=lines.split("\t")[0]+"\t"+lines.split("\t")[1]+"\t"+
								lines.split("\t")[2]+"\t"+
								(Integer.parseInt(lines.split("\t")[3])-sellCount)+"\t";
						line.add(lines2);
						isstock=true;
					}else { //��� ������
						line.add(lines);
						System.out.println("��� �����ϴ�.");
					}
				}else {
					line.add(lines);
				}
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int i=0;i<line.size();i++) {
				bw.write(line.get(i));
				bw.newLine();
				bw.flush();
			}
			bw.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		if(isstock) {
			int sumsales = 0;
			try {
				BufferedReader br2 = new BufferedReader(new FileReader(sales));
				sumsales = Integer.parseInt(br2.readLine())+pay;
				br2.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			try {
				FileWriter fw = new FileWriter(sales);
				fw.write(String.valueOf(sumsales));
				fw.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
}
