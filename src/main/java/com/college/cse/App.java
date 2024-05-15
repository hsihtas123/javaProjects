package com.college.cse;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.college.cse.model.Student;
import com.college.cse.service.StudentService;
import com.college.cse.service.StudentServiceImpl;
import com.college.cse.util.DbUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		Connection dbConnection = DbUtil.getDbConnection();
		if (dbConnection != null) {
			System.out.println("Connection Established " + dbConnection);
		} else {
			System.out.println("Connection not stablished");
		}
		
		Student student = new  Student(5, "Navira", 25, "IIT Madras");		
		StudentService studentService = new StudentServiceImpl();		
		boolean insertData = studentService.insertData(student);
		if(insertData) {
			System.out.println("Student Data Saved successfully with id " + student.getId());
		}
		else {
			System.out.println("Student Data not updated successfully with id ");
		}
		
		List<Student> studentDetails = studentService.getAllStudentDetails();
		studentDetails.forEach(std -> {
			System.out.println(std);
		});
		
		Scanner scan= new Scanner(System.in);
		
		System.out.println("Enter the id to search");
		int searchId = scan.nextInt();
		
		Student studentDetailsById = studentService.getStudentDetailsById(searchId);
		
		if(studentDetailsById != null) {
			System.out.println(studentDetailsById);
		}
		else {
			System.out.println("NoData found");
		}
	}
}
