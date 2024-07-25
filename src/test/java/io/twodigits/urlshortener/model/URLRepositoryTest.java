package io.twodigits.urlshortener.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class URLRepositoryTest {

	@Autowired
	private URLRepository repository;

	@Test
	public void testCreateAndFind() throws Exception {
		var inputUrl = "https://some.test.de/site?param=uno";
		var url = new URL();
		url.setUrl(inputUrl);
		var id = url.generateId();

		assertThat(url.getId()).isNotNull();
		System.err.println(url.getId());

		url = repository.save(url);
		assertThat(url.getId()).isEqualTo(id);

		var storedURl = repository.findById(Integer.toString(id));
		assertThat(storedURl).isPresent();
		assertThat(storedURl.get().getUrl()).isEqualTo(inputUrl);
	}
}
