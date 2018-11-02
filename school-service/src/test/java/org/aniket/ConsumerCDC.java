package org.aniket;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.aniket.controller.SchoolController;
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
@AutoConfigureStubRunner(ids = {"org.aniket:+:stubs:13186"},  workOffline = true)
public class ConsumerCDC {
//    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();
   
    @Test
    public void validateStocksForAll() throws Exception {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:13186/students/1", String.class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
//        assertThat(responseEntity.getBody(), equalTo( "{\"price\":\"1000\",\"season\":\"summer\",\"model\":\"Model X\",\"tireModel\":\"stealth\"}"));
    }
}