package org.aniket;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aniket.dto.StudentDTO;
import org.aniket.service.SchoolService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchoolControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SchoolService service;

	private List<StudentDTO> students=new ArrayList<>();
	private List<StudentDTO> emptyStudents=new ArrayList<>();
	
	@Before
	public void setUpService() {

		students.add(new StudentDTO("scott", 1));
		students.add(new StudentDTO("tiger", 1));
		
		//Happy case
	    Mockito.when(
	        service.getStudentsBySchoolId(1))
	    .thenReturn(students);
	    
	    //Empty case
	    Mockito.when(
		        service.getStudentsBySchoolId(2))
		    .thenReturn(emptyStudents);
	    
	    //Null case	when school is not present    
	    Mockito.when(
		        service.getStudentsBySchoolId(3))
		    .thenReturn(null);
	    
	    //Exception case	    
	    Mockito.when(
		        service.getStudentsBySchoolId(4))
		    .thenThrow(RuntimeException.class);

	}

	@Test
	public void SchoolController_ValidParamters_ReturnValidResponse() throws Exception {
		this.mockMvc.perform(get("/school/1/students")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("scott")))
				.andExpect(content().string(containsString("tiger")))
				.andExpect(content().string(containsString("1")));
		
	}
	
	@Test
	public void GetStudents_ValidParamters_ReturnEmptyResponse() throws Exception {
		this.mockMvc.perform(get("/school/2/students")).andDo(print())
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void GetStudents_ValidParamters_Return404() throws Exception {
		this.mockMvc.perform(get("/school/3/students")).andDo(print())
				.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void GetStudents_InvalidParamters_Return400() throws Exception {
		this.mockMvc.perform(get("/school/str/students")).andDo(print())
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void GetStudents_ValidParamters_Return5XX() throws Exception {
		this.mockMvc.perform(get("/school/4/students")).andDo(print())
				.andExpect(status().is5xxServerError());
		
	}
	
	
}
