package urlshortener;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import urlshortener.api.HomeController;
import urlshortener.service.URLShortenerService;
import urlshortener.service.URLShortenerServiceImpl;

@SpringBootTest
class SmokeTest {

	@Autowired
	private HomeController controller;

	@Autowired
	private URLShortenerService service;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();

		assertThat(controller.add(null)).isEqualTo("API root");

		assertThat(service).isNotNull();
		assertThat(service).isInstanceOf(URLShortenerServiceImpl.class);
	}

}
