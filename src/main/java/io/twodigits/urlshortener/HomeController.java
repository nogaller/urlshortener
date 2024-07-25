package io.twodigits.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.twodigits.urlshortener.service.URLShortenerService;

@Controller
public class HomeController {

	@Autowired
	private URLShortenerService service;

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "API root";
	}

	@RequestMapping("/new")
	public @ResponseBody String add(String url) {
		var shortUrl = service.addURL("userTODO", url);

		var baseURL = getBaseUrl();
		return baseURL + "/" + shortUrl.getId();
	}

	/** get Base URL of the service */
	private String getBaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}

}