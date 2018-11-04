package org.aniket.contract;

import java.util.ArrayList;
import java.util.List;

import org.aniket.controller.StudentController;
import org.aniket.domain.Student;
import org.aniket.service.StudentService;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class ProducerCDCTest {
	
	 @Mock
	 private StudentService studentService;
	 
	 
	  @Before
	  public void setup() {
		  List<Student> students=new ArrayList<>();
		  MockitoAnnotations.initMocks(this);

		  students.add(new Student("Scott", 1));
		  students.add(new Student("Tiger", 1));
		  
		  Mockito.when(
				  studentService.getStudentsBySchoolId(1))
			.thenReturn(students);
		  
	    RestAssuredMockMvc.standaloneSetup(new StudentController(studentService));
	  }
}
