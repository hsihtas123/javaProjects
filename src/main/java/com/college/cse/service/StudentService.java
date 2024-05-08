package com.college.cse.service;

import java.util.List;

import com.college.cse.model.Student;

public interface StudentService {
	// Insert
	boolean insertData(Student obj);
	// Select 
	List<Student> getAllStudentDetails() throws Exception;

}
