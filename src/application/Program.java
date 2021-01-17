package application;


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
	}
}
