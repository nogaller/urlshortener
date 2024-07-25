package io.twodigits.urlshortener;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MockServerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void returnDefaultMessage() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("API root")));
	}

	@Test
	void insertNewUrl() throws Exception {
		var url = "test";
		mockMvc.perform(post("/new").param("url", url)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("userTODO")))
				// TODO
				.andExpect(content().string("http://localhost/userTODO"));
	}
}