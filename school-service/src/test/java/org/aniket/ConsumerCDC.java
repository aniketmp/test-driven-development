package org.aniket;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(
		  workOffline = true,
		  ids = "org.aniket:student-service:+:stubs:8090")
public class ConsumerCDC {
	
    TestRestTemplate restTemplate = new TestRestTemplate();
   
    @Test
    public void validateStocksForAll() throws Exception {
    	System.out.println("Executing consumer CDC");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:8090/students/1", String.class);
        System.out.println("Response from stub:"+responseEntity.getBody());
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(responseEntity.getBody(), equalTo( "[{\"name\":\"Scott\",\"schoolId\":1},{\"name\":\"Tiger\",\"schoolId\":1}]"));
    }
}