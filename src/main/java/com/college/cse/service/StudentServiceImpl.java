package com.college.cse.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.college.cse.model.Student;
import com.college.cse.util.DbUtil;

public class StudentServiceImpl implements StudentService {
	@Override
	public boolean insertData(Student obj) {
		// Parameterized query
		String insertSql ="insert into student(sid,sname,sage,scollege) values(?,?,?,?)";
		//A connection (session) with a specific database. SQL statements are executed and results are returned within the context of a connection. 
		Connection dbConnection = DbUtil.getDbConnection();
		//An object that represents a precompiled SQL statement. 
		PreparedStatement preparedStatement = null;
		if(null!= dbConnection) {
			try {
				//3. Create Statement 
				preparedStatement =dbConnection.prepareStatement(insertSql);				
				preparedStatement.setInt(1, obj.getId());
				preparedStatement.setString(2, obj.getName());
				preparedStatement.setInt(3, obj.getAge());
				preparedStatement.setString(4, obj.getCollege());				
				//4. execute query
				// insert / delete/update
				int noOfRowsReturned = preparedStatement.executeUpdate();
				if(noOfRowsReturned>0) {
					return true;
				}				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					//5 close connection
					preparedStatement.close();
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		return false;
	}

	@Override
	public List<Student> getAllStudentDetails() throws SQLException {
		
		List<Student> studentList = new ArrayList<>();
		String selectQuery = "select * from student";		
		Connection dbConnection = DbUtil.getDbConnection();
		PreparedStatement preparedStatement = null;
		if(null != dbConnection) {			
			try {
				preparedStatement = dbConnection.prepareStatement(selectQuery);
				//Moves the cursor forward one row from its current position
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next())  {
					Student student = new Student();
					int id = resultSet.getInt("sid");
					student.setId(id);
					student.setName(resultSet.getString("sname"));
					student.setAge(resultSet.getInt("sage"));
					student.setCollege(resultSet.getString("scollege"));
					studentList.add(student);					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {				
				preparedStatement.close();
				dbConnection.close();
			}
		}
		return studentList;
	}

}
