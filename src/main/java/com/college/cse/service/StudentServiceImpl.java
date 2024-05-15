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
		String updateSql = "update student set scollege=? where sid=?";
		//A connection (session) with a specific database. 
		//SQL statements are executed and results are returned within the context of a connection. 
		Connection dbConnection = DbUtil.getDbConnection();
		//An object that represents a precompiled SQL statement. 
		PreparedStatement preparedStatement = null;
		if(null!= dbConnection) {
			try {
				//3. Create Statement 
				preparedStatement =dbConnection.prepareStatement(updateSql);				
				preparedStatement.setString(1,obj.getCollege());
				preparedStatement.setInt(2, obj.getId());
//				preparedStatement.setInt(3, obj.getAge());
//				preparedStatement.setString(4, obj.getCollege());				
				//4. execute query or statement
				// insert / delete/update
				
				int noOfRowsReturned = preparedStatement.executeUpdate();
				System.out.println("-->" + noOfRowsReturned);
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
		String selectQuery = "select * from student where sid";		
		Connection dbConnection = DbUtil.getDbConnection();
		PreparedStatement preparedStatement = null;
		if(null != dbConnection) {			
			try {
				preparedStatement = dbConnection.prepareStatement(selectQuery);
				//Moves the cursor forward one row from its current position
				// 5 . Get the result
				// select - executeQuery
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
	
	
	@Override
	public Student getStudentDetailsById(int studentId) throws SQLException {
		
		String selectQuery = "select * from student where sid = ?";		
		Connection dbConnection = DbUtil.getDbConnection();
		PreparedStatement preparedStatement = null;
		if(null != dbConnection) {			
			try {
				preparedStatement = dbConnection.prepareStatement(selectQuery);
				preparedStatement.setInt(1, studentId);
				//Moves the cursor forward one row from its current position
				// 5 . Get the result
				// select - executeQuery
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next())  {
					Student student = new Student();
					int id = resultSet.getInt("sid");
					student.setId(id);
					student.setName(resultSet.getString("sname"));
					student.setAge(resultSet.getInt("sage"));
					student.setCollege(resultSet.getString("scollege"));
					return student;				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {				
				preparedStatement.close();
				dbConnection.close();
			}
		}
		return null;
	}


}
