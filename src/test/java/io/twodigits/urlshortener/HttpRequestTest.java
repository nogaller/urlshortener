package io.twodigits.urlshortener;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String sut;

	@BeforeEach
	void init() {
		sut = "http://localhost:" + port + "/";
	}

	@Test
	void returnDefaultMessage() throws Exception {
		assertThat(restTemplate.getForObject(sut, String.class)).contains("API root");
	}

	@Test
	void insertNewUrl() throws Exception {
		var url = "test";
		assertThat(restTemplate.getForObject(sut + "new?url=" + url, String.class)).contains("userTODO");
	}
}