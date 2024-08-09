package urlshortener.api;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import urlshortener.model.URL;

/**
 * URL Transfer object
 */
public class UrlTO {

	private String shortUrl;
	private String longUrl;

	public UrlTO(URL url) {
		shortUrl = toHexadecimalIdUrl(url);
		longUrl = url.getUrl();
	}

	public UrlTO(String shortUrl, String longUrl) {
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
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

	/** get Base URL of the service */
	private final String getBaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}

	/**
	 * Reverse function: from HEX int to decimal int
	 *
	 * @param id
	 * @return
	 */
	public Integer toUrlID() {
		return Integer.parseUnsignedInt(shortUrl, 16);
	}

	public URL toUrl() {
		var url = new URL();
		url.setUrl(longUrl);
		url.setId(toUrlID());
		return url;
	}

}
