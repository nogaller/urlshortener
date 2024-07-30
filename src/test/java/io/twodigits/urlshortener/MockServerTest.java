package io.twodigits.urlshortener;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import io.twodigits.urlshortener.model.URLRepository;

@SpringBootTest
@AutoConfigureMockMvc
class MockServerTest {

	static final String BASE_URL = "http://localhost/";
	static final String TEST_URL = "https://www.google.com/hello?param=world";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private URLRepository repository;

	@BeforeEach
	void init() {
		// Cleanup DB on each run
		repository.deleteAll();
	}

	@Test
	void returnDefaultMessage() throws Exception {
		mockMvc.perform(get("/"))
//		.andDo(print())
				.andExpect(status().isOk())//
				.andExpect(content().string(containsString("API root")));
	}

	@Test
	MvcResult insertNewUrl() throws Exception {
		return mockMvc.perform(post("/").param("url", TEST_URL))
//		.andDo(print())
				.andExpect(status().isOk())//
				.andExpect(content().string(containsString(BASE_URL))).andReturn();

	}

	@Test
	void list() throws Exception {
		insertNewUrl();

		mockMvc.perform(post("/list"))
//		.andDo(print())
				.andExpect(status().isOk())//
				.andExpect(content().string(containsString(BASE_URL)))
				.andExpect(content().string(containsString(TEST_URL)));
	}

	@Test
	void listJSON() throws Exception {
		var result = insertNewUrl();
		var content = result.getResponse().getContentAsString();

		var id = content.substring(BASE_URL.length());

		mockMvc.perform(post("/list/json"))
//		.andDo(print())
				.andExpect(status().isOk())//
				.andExpect(content()//
						.json("[{\"shortUrl\":\"" + BASE_URL + id + "\",\"longUrl\":\"" + TEST_URL + "\"}]"));
	}

	@Test
	void listXML() throws Exception {
		var result = insertNewUrl();
		var content = result.getResponse().getContentAsString();

		var id = content.substring(BASE_URL.length());

		mockMvc.perform(post("/list/xml"))
//		.andDo(print())
				.andExpect(status().isOk())//
				.andExpect(content()//
						.xml("<List><item><shortUrl>" + BASE_URL + id + "</shortUrl><longUrl>" + TEST_URL
								+ "</longUrl></item></List>"));
	}

	@Test
	void getStoredURLinvalid() throws Exception {
		insertNewUrl();

		var id = 0x123abc;

		mockMvc.perform(post("/" + id))//
//				.andDo(print(System.err))//
				.andExpect(status().isOk())//
				.andExpect(forwardedUrl("invalid short URL"));
	}

	@Test
	void getStoredURL() throws Exception {
		var result = insertNewUrl();
		var content = result.getResponse().getContentAsString();

		var id = content.substring(BASE_URL.length());

		mockMvc.perform(post("/" + id))//
//				.andDo(print(System.err))//
				.andExpect(status().is3xxRedirection())//
				.andExpect(redirectedUrl(TEST_URL));
	}

	@Test
	void updateURL() throws Exception {
		var result = insertNewUrl();
		var content = result.getResponse().getContentAsString();

		var id = content.substring(BASE_URL.length());

		final var newTestUrl = "http://new/test";
		mockMvc.perform(post("/" + id).param("url", newTestUrl))
//				.andDo(print())
				.andExpect(status().isOk())//
				.andExpect(forwardedUrl(newTestUrl));
	}

	@Test
	void deleteURL() throws Exception {
		// insert URL
		var result = insertNewUrl();
		var content = result.getResponse().getContentAsString();

		var id = content.substring(BASE_URL.length());

		// remove existing URL
		mockMvc.perform(delete("/" + id)).andExpect(status().isOk());

		// URL removed, should not be there
		mockMvc.perform(post("/" + id))
//		.andDo(print())
				.andExpect(status().isOk())//
				.andExpect(forwardedUrl("invalid short URL"));
	}

}