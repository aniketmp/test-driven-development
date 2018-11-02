package org.aniket;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aniket.assemblers.DTOAssembler;
import org.aniket.domain.Student;
import org.aniket.dto.StudentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AssemblerTest {
	
	  @Test
	  public void BuildStudentlDTOFrom_ValidSchoolDomain_ShouldCorrectMapDTO() {

		List<Student> input=new ArrayList<>();
		input.add(new Student("Scott", 1));
		
		StudentDTO expected=new StudentDTO("Scott", 1);
	    
		StudentDTO actual = DTOAssembler.buildStudentDTOFrom(input).get(0);

	    assertNotNull(actual);
	    assertEquals(expected, actual);
	}

}
