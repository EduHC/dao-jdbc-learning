package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {
		new Program2();
	}
	
	public Program2() {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department department = departmentDao.findById(3);
		System.out.println("=== Teste 1: Department findById ===");
		System.out.println(department);
	}
}
