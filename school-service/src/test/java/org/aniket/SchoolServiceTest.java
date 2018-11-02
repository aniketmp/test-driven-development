package org.aniket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import org.aniket.dto.StudentDTO;
import org.aniket.exception.StudentServiceUnavailable;
import org.aniket.service.SchoolService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@RestClientTest(SchoolService.class)
@Import({AppConfig.class})
public class SchoolServiceTest {
	
	@Value("${student-url}")
	private String studentUrl;
	
    @Autowired
    private SchoolService client;
    
    @Autowired
    private MockRestServiceServer server;
 
    @Autowired
    private ObjectMapper objectMapper;
    
    private String studentsString =null;
    private String emptyStudentsString =null;
    
    private List<StudentDTO> students=new ArrayList<>();
	private List<StudentDTO> emptyStudents=new ArrayList<>();
    
    @Before
    public void setUp() throws Exception {
    	students.add(new StudentDTO("scott", 1));
		students.add(new StudentDTO("tiger", 1));
		
        studentsString = 
          objectMapper.writeValueAsString(students);  
        
        emptyStudentsString = 
                objectMapper.writeValueAsString(emptyStudents);      
      
    }
    
    @Test
    public void GetStudentsBySchoolId_ValidInput_ShouldNotBeNullOrEmpty() {
 
    	 //Happy test case
        this.server.expect(requestTo(studentUrl+"/students/1"))
          .andRespond(withSuccess(studentsString, MediaType.APPLICATION_JSON));
        
    	List<StudentDTO> studentList = this.client.getStudentsBySchoolId(1);
    	assertNotNull(studentList);
    	assertEquals(studentList.size(), 2);
        
    }
    
    @Test
    public void GetStudentsBySchoolId_EmptyInput_ShoulNotBeNull() {
    	
    	//Empty response
        this.server.expect(requestTo(studentUrl+"/students/2"))
        .andRespond(withSuccess(emptyStudentsString, MediaType.APPLICATION_JSON));
 
    	List<StudentDTO> studentList = this.client.getStudentsBySchoolId(2);
    	assertNotNull(studentList);
    	assertEquals(studentList.size(), 0);
        
    }
    
    @Test
    public void GetStudentsBySchoolId_BlankInput_ShoulBeNull() {
    	
    	//Null response
        this.server.expect(requestTo(studentUrl+"/students/3"))
        .andRespond(withSuccess("", MediaType.APPLICATION_JSON));
 
    	List<StudentDTO> studentList = this.client.getStudentsBySchoolId(3);
    	assertNull(studentList);
        
    }
    
    @Test(expected = StudentServiceUnavailable.class)
    public void GetStudentsBySchoolId_ValidInput_ShoulPropogate404Status() {
    	
    	//Empty response
    	 this.server
         .expect(requestTo(studentUrl+"/students/4"))         
         .andRespond(withStatus(HttpStatus.NOT_FOUND));
    	 
    	this.client.getStudentsBySchoolId(4);
    }
 

}
