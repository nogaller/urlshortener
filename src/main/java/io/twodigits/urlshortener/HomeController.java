package io.twodigits.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.twodigits.urlshortener.service.URLShortenerService;

@Controller
public class HomeController {

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

		var baseURL = getBaseUrl();
		return baseURL + "/" + shortUrl.getId();
	}

	/** get Base URL of the service */
	private final String getBaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}

}