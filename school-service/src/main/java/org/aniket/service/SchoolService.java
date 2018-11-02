package org.aniket.service;

import java.util.List;

import org.aniket.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SchoolService {
    
	 @Autowired
	 private RestTemplate restTemplate;
	
	 @Value("${student-url}")
	 private String studentUrl;
	 
	public List<StudentDTO> getStudentsBySchoolId(int schoolId) 
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		String url= studentUrl+"/students/"+schoolId;
		HttpEntity<List<StudentDTO>> response = restTemplate.exchange(
				url, 
		        HttpMethod.GET, 
		        entity, 
		        new ParameterizedTypeReference<List<StudentDTO>>(){});
		List<StudentDTO> studentList=response.getBody(); 
		return studentList;
	}
}