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
	int addURL() {
		var url = service.addURL(null, "test");
		assertThat(url.getId()).isNotNull();

		return url.getId();
	}

	@Test
	void listURLs() {
		var urls = service.listURLs(null);
		assertThat(urls).hasSize(0);

		var savedId = addURL();

		urls = service.listURLs(null);
		assertThat(urls).hasSize(1);
		var url = urls.iterator().next();
		assertThat(url.getId()).isEqualTo(savedId);
	}

	@Test
	void getURLbyID() {
		// TODO
	}
}
