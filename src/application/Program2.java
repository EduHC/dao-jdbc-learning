package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {
		new Program2();
	}
	
	public Program2() {
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department department = departmentDao.findById(3);
		System.out.println("=== Teste 1: Department findById ===");
		System.out.println(department);
		
		
		System.out.println("\n=== Teste 2: Department findAll ===");
		List<Department> departmentList = departmentDao.findAll();
		
		for(Department dep : departmentList) {
			System.out.println(dep);
		}
		
		System.out.println("\n=== Teste 3: Department deleteById ===");
		System.out.println("Insert an id to delete the department: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Deletion completed!");
		
		System.out.println("\n=== Teste 4: Department insertion ===");
		department = new Department("Varejo", null);
		departmentDao.insert(department);
		System.out.println("Inserted! New id = " + department.getId());
		sc.close();
		
		System.out.println("\n=== TESTE 5: seller update ===");
		department = departmentDao.findById(6);
		department.setName("Almoxarifado");
		departmentDao.update(department);	
		System.out.println("Update Completed!");
	}
}
