package io.twodigits.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return url;
	}

}