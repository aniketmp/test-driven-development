package org.aniket.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.aniket.domain.Student;
import org.aniket.dto.StudentDTO;

public class DTOAssembler {

	public static List<StudentDTO> buildStudentDTOFrom(List<Student> students) {
		List<StudentDTO> studentDTOs=new ArrayList<>();
		for(Student student:students)
		{
			studentDTOs.add(new StudentDTO(student.getName(), student.getSchoolId()));
		}
		return studentDTOs;
	}

}
