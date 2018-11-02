package org.aniket.controller;

import java.util.List;

import org.aniket.assemblers.DTOAssembler;
import org.aniket.domain.Student;
import org.aniket.dto.StudentDTO;
import org.aniket.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class StudentController {

    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    
    @GetMapping("/students/{schoolId}")
    public ResponseEntity<List<StudentDTO>> getStudents(@PathVariable int schoolId) {
    	List<Student> students=service.getStudentsBySchoolId(schoolId);    	
    	List<StudentDTO> studentsDTO=DTOAssembler.buildStudentDTOFrom(students);
    	return ResponseEntity.ok(studentsDTO);
    }

}
