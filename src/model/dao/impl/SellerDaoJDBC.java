package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection connection;
	
	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insert(Seller object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(
					"SELECT SELLER.*, DEPARTMENT.NAME AS Department "
					+ "FROM SELLER "
					+ "INNER JOIN DEPARTMENT "
					+ "ON SELLER.DepartmentId = DEPARTMENT.Id "
					+ "WHERE SELLER.Id = ?");
			
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				Department department = instantiateDepartment(result);
				
				Seller seller = instantiateSeller(result, department);
				
				return seller;
			}	
			return null;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(statement);
		}
	}

	private Seller instantiateSeller(ResultSet result, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(result.getInt("Id"));
		seller.setName(result.getString("Name"));
		seller.setEmail(result.getString("Email"));
		seller.setBirthDate(result.getDate("BirthDate"));
		seller.setBaseSalary(result.getDouble("BaseSalary"));
		seller.setDepartment(department);
		
		return seller;
	}

	private Department instantiateDepartment(ResultSet result) throws SQLException {
		Department department = new Department();
		department.setId(result.getInt("DepartmentId"));
		department.setName(result.getString("Department"));
		
		return department;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(
					"SELECT SELLER.*, DEPARTMENT.NAME AS Department "
					+ "FROM SELLER "
					+ "INNER JOIN DEPARTMENT "
					+ "ON SELLER.DepartmentId = DEPARTMENT.Id "
					+ "ORDER BY Name");
			
			result = statement.executeQuery();
			
			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> departmentMap = new HashMap<>();
			
			while(result.next()) {
				
				Department departmentTmp = departmentMap.get(result.getInt("DepartmentId"));
				
				if(departmentTmp == null) {
					 departmentTmp = instantiateDepartment(result);
					 departmentMap.put(result.getInt("DepartmentId"), departmentTmp);
				}
				/*usado esse método de instanciação de Map para que o relacionamento dos objetos fique correto
				 evitando que seja instanciado mais de um objeto do mesmo departamento */
				
				Seller seller = instantiateSeller(result, departmentTmp);
				
				sellerList.add(seller);
			}	
			return sellerList;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(statement);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(
					"SELECT SELLER.*, DEPARTMENT.NAME AS Department "
					+ "FROM SELLER "
					+ "INNER JOIN DEPARTMENT "
					+ "ON SELLER.DepartmentId = DEPARTMENT.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			statement.setInt(1, department.getId());
			result = statement.executeQuery();
			
			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> departmentMap = new HashMap<>();
			
			while(result.next()) {
				
				Department departmentTmp = departmentMap.get(result.getInt("DepartmentId"));
				
				if(departmentTmp == null) {
					 departmentTmp = instantiateDepartment(result);
					 departmentMap.put(result.getInt("DepartmentId"), departmentTmp);
				}
				/*usado esse método de instanciação de Map para que o relacionamento dos objetos fique correto
				 evitando que seja instanciado mais de um objeto do mesmo departamento */
				
				Seller seller = instantiateSeller(result, departmentTmp);
				
				sellerList.add(seller);
			}	
			return sellerList;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(statement);
		}
	}
	
}
