package application;

import java.util.Date;

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
	// utilizando o Factory faz-se uma dependency injection e o programa n�o conhece a implementa��o, mas apenas a interface

		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
	}
}
