package io.twodigits.urlshortener.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class URLRepositoryTest {

	@Autowired
	private URLRepository repository;

	@Test
	public void testCreateAndFind() throws Exception {
		var inputUrl = "https://some.test.de/site?param=uno";
		var url = new URL(inputUrl);
		var id = url.getId();

		assertThat(url.getId()).isNotEqualTo(0);

		url = repository.save(url);
		assertThat(url.getId()).isEqualTo(id);

		var storedURl = repository.findById(id);
		assertThat(storedURl).isPresent();
		assertThat(storedURl.get().getUrl()).isEqualTo(inputUrl);

		repository.save(new URL("www.tmp.de"));
		var list = repository.findByUrlContainingIgnoreCase("test.de");
		assertThat(list).hasSize(1);
		list = repository.findByUrlContainingIgnoreCase(".de");
		assertThat(list).hasSize(2);
	}
}
