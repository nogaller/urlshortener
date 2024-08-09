package urlshortener.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import urlshortener.model.URL;
import urlshortener.model.URLRepository;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {

	private static final Logger logger = LoggerFactory.getLogger(URLShortenerServiceImpl.class);

	@Autowired
	private URLRepository repository;

	@Override
	public Iterable<URL> listURLs(String user, String filter) {
		return ObjectUtils.isEmpty(filter) ? repository.findAll() : repository.findByUrlContainingIgnoreCase(filter);
	}

	@Override
	public URL addURL(String user, String urlStr) {
		var url = new URL(urlStr);

		logger.info("Generated URL#id " + url.getId());
		return repository.save(url);
	}

	@Override
	public Optional<URL> getURL(String user, Integer id) {
		return getURL(id);
	}

	@Override
	public Optional<URL> getURL(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteURL(String user, Integer id) {
		repository.deleteById(id);
	}

	@Override
	public void updateURL(String user, String url, Integer id) {
		repository.findById(id).ifPresent(it -> {
			it.setUrl(url);
			repository.save(it);
		});
	}

}
