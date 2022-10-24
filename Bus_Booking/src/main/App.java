package main;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out.print("Vui lòng chọn 0: Quản lí, 1: Khách Hàng => ");
		Scanner sc = new Scanner(System.in);
		String op = sc.nextLine();
		
		while(!op.equals("0") && !op.equals("1")) {
			System.out.print("Lựa chọn không tồn tại, vui lòng nhập lại: ");
			op = sc.nextLine();
		}
	
		if(op.equals("0")) {
			LoginAsManager.main(null);
		}
		else{
			LoginAsCustomer.main(null);
		}
		//Hello World 
	}
}
