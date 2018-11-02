package org.aniket.service;

import java.util.List;

import org.aniket.dao.StudentDAO;
import org.aniket.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    
	 @Autowired
	 private StudentDAO dao;
	 
	 
	public List<Student> getStudentsBySchoolId(int schoolId) 
	{		
		List<Student> studentList=dao.getBySchoolId(schoolId); 
		return studentList;
	}
}