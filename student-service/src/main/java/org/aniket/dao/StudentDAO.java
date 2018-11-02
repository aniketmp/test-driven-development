package org.aniket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.aniket.domain.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
	List<Student> students;
	
	@PostConstruct
	public void initialize() {
		students=new ArrayList<>();
		
		students.add(new Student("Scott",1));
		students.add(new Student("Tiger",1));
		
	}

	public List<Student> getBySchoolId(int schoolId) {
		return students;
	}

}
