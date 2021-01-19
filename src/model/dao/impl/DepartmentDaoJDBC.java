package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	Connection connection;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(
					"SELECT * "
					+ "FROM DEPARTMENT "
					+ "WHERE Id = ?");
			
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				return instantiateDepartment(result);
			}
			return null;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
			DB.closeResultSet(result);
		}
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private Department instantiateDepartment(ResultSet result) throws SQLException {
		Department department = new Department();
		department.setId(result.getInt("Id"));
		department.setName(result.getString("Name"));
		
		return department;
	}
}
