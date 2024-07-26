package io.twodigits.urlshortener;

import static org.apache.logging.log4j.util.Strings.LINE_SEPARATOR;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * A user can CREATE - add a website URL for which a short URL is created and
	 * stored in the database.<br>
	 * The short URL must have a unique ID consisting of alphanumeric characters.
	 *
	 * @param url
	 * @return
	 */
	@RequestMapping("/")
	public @ResponseBody String add(String url) {
		if (ObjectUtils.isEmpty(url))
			return "API root";

		var shortUrlId = service.addURL("userTODO", url);

		return toHexadecimalIdUrl(shortUrlId);
	}

	/**
	 * User can READ original URL from short one<br>
	 * OR UPDATE
	 *
	 * @param id
	 * @return Stored Url for given short URL, or new URL (use exinsing Link to
	 *         stored)
	 */
	@RequestMapping("/{id}")
	public String getURL(@PathVariable String id, String url) {
		var intId = fromHexadeimalID(id);
		var storedUrl = service.getURL("userTODO", intId);

		if (storedUrl.isEmpty())
			return "invalid short URL";

		// find stored URL
		if (url == null)
			return "redirect:" + storedUrl.get().getUrl();

		// update URL
		service.updateURL("userTODO", url, intId);
		return url;
	}

	/**
	 * DELETE stored URL at ID
	 *
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public @ResponseBody String deleteURL(@PathVariable String id) {
		var intId = fromHexadeimalID(id);
		service.deleteURL("userTODO", intId);
		return null;
	}

	/**
	 * User can List All stored {@link URL}'s
	 *
	 * @return
	 */
	@RequestMapping("/list")
	public @ResponseBody String list() {
		var urls = service.listURLs(null);
		return StreamSupport.stream(urls.spliterator(), false)//
				.map(it -> toHexadecimalIdUrl(it) + " -> " + it.getUrl())//
				.collect(Collectors.joining("<br>" + LINE_SEPARATOR));
	}

	/**
	 * from int ID to shorter HEX int
	 *
	 * @param shortUrl
	 * @return
	 */
	private String toHexadecimalIdUrl(URL shortUrl) {
		var baseURL = getBaseUrl();
		return baseURL + "/" + Integer.toHexString(shortUrl.getId());
	}

	/**
	 * Reverse function: from HEX int to decimal int
	 *
	 * @param id
	 * @return
	 */
	private int fromHexadeimalID(String id) {
		return Integer.parseUnsignedInt(id, 16);
	}

	/** get Base URL of the service */
	private final String getBaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}

}