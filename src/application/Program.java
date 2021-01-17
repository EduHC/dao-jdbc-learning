package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department object = new Department("Books", 1);
		Seller seller = new Seller(10, "Eduardo", "eduardo@mail.com", new Date(), 3000.00, object);
		
		System.out.println(seller);
	}
}
