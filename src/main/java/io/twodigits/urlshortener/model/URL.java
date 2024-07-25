package io.twodigits.urlshortener.model;

public class URL {

	/**
	 * The unique ID of an URL
	 */
	private final String id;

	/**
	 * The URL for which a short URL is provided
	 */
	private final String url;

	/**
	 * Initialize with immutable User+ID
	 * 
	 * @param id
	 * @param url
	 */
	public URL(String id, String url) {
		this.id = id;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getId() {
		return id;
	}

}
