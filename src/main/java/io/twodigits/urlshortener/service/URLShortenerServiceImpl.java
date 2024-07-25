package io.twodigits.urlshortener.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.twodigits.urlshortener.model.URL;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {

	@Override
	public Iterable<URL> listURLs(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL addURL(String user, String url) {
		return new URL(user, url);
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
