package urlshortener;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import urlshortener.model.URLRepository;
import urlshortener.service.URLShortenerService;

@SpringBootTest
@AutoConfigureMockMvc
public class URLShortenerServiceTest {

	@Autowired
	private URLShortenerService service;

	@Autowired
	private URLRepository repository;

	@BeforeEach
	void init() {
		// Cleanup DB on each run
		repository.deleteAll();
	}

	@Test
	int addURL() {
		var url = service.addURL(null, "test");
		assertThat(url.getId()).isNotNull();

		return url.getId();
	}

	@Test
	void listURLs() {
		var urls = service.listURLs(null, null);
		assertThat(urls).hasSize(0);

		var savedId = addURL();

		urls = service.listURLs(null, null);
		assertThat(urls).hasSize(1);
		var url = urls.iterator().next();
		assertThat(url.getId()).isEqualTo(savedId);

		// and filter
		urls = service.listURLs(null, "es");
		assertThat(urls).hasSize(1);

		// filter not matched
		urls = service.listURLs(null, "pq");
		assertThat(urls).isEmpty();
	}

	@Test
	void getURLbyID() {
		var savedId = addURL();

		var url = service.getURL(savedId);
		assertThat(url).isPresent();
		assertThat(url.get().getId()).isEqualTo(savedId);
		assertThat(url.get().getUrl()).isEqualTo("test");
	}

	@Test
	void getURLbyIDandUser() {
		var savedId = addURL();

		var url = service.getURL("user", savedId);
		assertThat(url).isPresent();
		assertThat(url.get().getId()).isEqualTo(savedId);
		assertThat(url.get().getUrl()).isEqualTo("test");
	}

	@Test
	void updateURL() {
		var savedId = addURL();

		service.updateURL(null, "new test", savedId);

		var url = service.getURL(savedId);
		assertThat(url).isPresent();
		assertThat(url.get().getUrl()).isEqualTo("new test");
	}
}
