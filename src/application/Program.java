package application;


import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		new Program();
	}
	
	public Program() {
		SellerDao sellerDao = DaoFactory.createSellerDao(); 
	
		Seller seller = sellerDao.findById(3);
	
		
		System.out.println("=== TESTE 1: seller findById ===");
		System.out.println(seller);
		
		System.out.println("\n=== TESTE 2: seller findByDepartment ===");
		Department dep = new Department(null, 2);
		List<Seller> sellersList = sellerDao.findByDepartment(dep);
		
		for(Seller sellerTmp : sellersList) {
			System.out.println(sellerTmp);
		}
		
		System.out.println("\n=== TESTE 3: seller findAll ===");
		sellersList = sellerDao.findAll();
		
		for(Seller sellerTmp : sellersList) {
			System.out.println(sellerTmp);
		}
		
		
		System.out.println("\n=== TESTE 4: seller insert ===");
		Seller newSeller = new Seller(null, "Gregório", "greg@mail.com", new Date(), 2500.00, dep);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
	}
}
