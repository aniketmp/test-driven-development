package org.aniket;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void SchoolController_ValidParamters_ReturnValidResponse() throws Exception {
		this.mockMvc.perform(get("/students/1")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Scott")))
				.andExpect(content().string(containsString("Tiger")))
				.andExpect(content().string(containsString("1")));
		
	}
	
}
