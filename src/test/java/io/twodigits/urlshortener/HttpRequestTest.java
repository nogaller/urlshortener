package io.twodigits.urlshortener;

import static io.twodigits.urlshortener.MockServerTest.TEST_URL;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.twodigits.urlshortener.model.URLRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private URLRepository repository;

	private String sut;

	@BeforeEach
	void init() {
		sut = "http://localhost:" + port + "/";
		// Cleanup DB on each run
		repository.deleteAll();
	}

	@Test
	void returnDefaultMessage() throws Exception {
		assertThat(restTemplate.getForObject(sut, String.class)).contains("API root");
	}

	@Test
	String insertNewUrl() throws Exception {
		var result = restTemplate.getForObject(sut + "?url=" + TEST_URL, String.class);
		assertThat(result).startsWith(sut);
		return result;
	}

	@Test
	void list() throws Exception {
		var shortUrl = insertNewUrl();
		assertThat(restTemplate.getForObject(sut + "list", String.class))//
				.contains(shortUrl)//
				.contains(TEST_URL);

		assertThat(restTemplate.getForObject(sut + "list?filter=FAIL", String.class))//
				.isNullOrEmpty();
	}

	@Test
	void listJSON() throws Exception {
		var shortUrl = insertNewUrl();
		var result = restTemplate.getForObject(sut + "list/json", String.class);
		logger.info(result);

		assertThat(result)//
				.contains(shortUrl)//
				.contains(TEST_URL);
	}

	@Test
	void listXML() throws Exception {
		var shortUrl = insertNewUrl();
		var result = restTemplate.getForObject(sut + "list/xml", String.class);
		logger.info(result);

		assertThat(result)//
				.contains(shortUrl)//
				.contains(TEST_URL);
	}

	@Test
	void listXMLandFilter() throws Exception {
		insertNewUrl();
		restTemplate.getForObject(sut + "?url=FAIL", String.class);

		var result = restTemplate.getForObject(sut + "list/xml?filter=google", String.class);
		logger.info(result);
		assertThat(result)//
				.contains("google")//
				.doesNotContain("FAIL");
	}
}