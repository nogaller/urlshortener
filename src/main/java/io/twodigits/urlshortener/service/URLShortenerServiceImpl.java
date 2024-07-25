package io.twodigits.urlshortener.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.URLRepository;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {

	private static final Logger logger = LoggerFactory.getLogger(URLShortenerServiceImpl.class);

	@Autowired
	private URLRepository repository;

	@Override
	public Iterable<URL> listURLs(String user) {
		return repository.findAll();
	}

	@Override
	public URL addURL(String user, String urlStr) {
		var url = new URL(urlStr);

		logger.info("Generated URL#id " + url.getId());
		return repository.save(url);
	}

	@Override
	public Optional<URL> getURL(String user, String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<URL> getURL(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteURL(String user, String id) {
		// TODO Auto-generated method stub

	}

}
