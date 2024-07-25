package io.twodigits.urlshortener;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import io.twodigits.urlshortener.service.URLShortenerService;

//TODO
@SpringBootTest
@AutoConfigureMockMvc
public class URLShortenerServiceTest {

	@Autowired
	private URLShortenerService service;

	@Test
	public void addURL() throws Exception {
		var url = service.addURL(null, "test");
		assertThat(url.getId()).isNotNull();
		// TODO
		System.err.println(url.getId());
	}
}
