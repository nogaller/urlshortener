package io.twodigits.urlshortener;

import static org.apache.logging.log4j.util.Strings.LINE_SEPARATOR;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.service.URLShortenerService;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private URLShortenerService service;

	/**
	 * A user can add a website URL for which a short URL is created and stored in
	 * the database.<br>
	 * The short URL must have a unique ID consisting of alphanumeric characters.
	 *
	 * @param url
	 * @return
	 */
	@RequestMapping("/")
	public @ResponseBody String add(String url) {
		if (ObjectUtils.isEmpty(url))
			return "API root";

		var shortUrl = service.addURL("userTODO", url);

		return toStoredStringUrl(shortUrl);
	}

	@RequestMapping("/list")
	public @ResponseBody String list() {
		var urls = service.listURLs(null);
		return StreamSupport.stream(urls.spliterator(), false)//
				.map(it -> toStoredStringUrl(it) + " -> " + it.getUrl())//
				.collect(Collectors.joining("<br>" + LINE_SEPARATOR));
	}

	private String toStoredStringUrl(URL shortUrl) {
		var baseURL = getBaseUrl();
		return baseURL + "/" + Integer.toHexString(shortUrl.getId());
	}

	/** get Base URL of the service */
	private final String getBaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}

}