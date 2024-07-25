package io.twodigits.urlshortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class URL {

	/**
	 * The unique ID of an URL
	 */
	@Id
	private String id;

	/**
	 * The URL for which a short URL is provided
	 */
	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	/**
	 * When {@link #url} is set, trigger ID generation.<br>
	 * Set to NULL otherwise<br>
	 * PS: must be called once {@link #url} is set
	 *
	 * @return
	 */
	public String generateId() {
		id = url == null ? null : Integer.toHexString(url.hashCode());
		return id;
	}

}
