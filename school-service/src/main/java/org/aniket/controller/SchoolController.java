package org.aniket.controller;

import java.util.List;

import org.aniket.dto.StudentDTO;
import org.aniket.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SchoolController {

    private SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public ResponseEntity<String> getRoot() {
    	return ResponseEntity.ok("OK");
    }
    
    @GetMapping("/school/{schoolId}/students")
    public ResponseEntity<List<StudentDTO>> getStudents(@PathVariable int schoolId) {
    	List<StudentDTO> students=service.getStudentsBySchoolId(schoolId);
    	if(students==null)
    	{
    		return ResponseEntity.notFound().build();
    	}
    	if(students.isEmpty())
    	{
    		return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.ok(students);
    }

}
