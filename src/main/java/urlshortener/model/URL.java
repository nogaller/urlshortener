package urlshortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class URL {

	/**
	 * The unique ID of an URL
	 */
	@Id
	private int id;

	/**
	 * The URL for which a short URL is provided
	 */
	private String url;

	/** default constructor */
	public URL() {
	}

	/**
	 * Constructor will create new Entry and generate Id
	 *
	 * @param urlStr
	 */
	public URL(String urlStr) {
		url = urlStr;
		generateId();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	/**
	 * When {@link #url} is set, trigger ID generation.<br>
	 * Set to NULL otherwise<br>
	 * PS: must be called once {@link #url} is set
	 *
	 * @return
	 */
	public int generateId() {
		id = url == null ? 0 : url.hashCode();
		return id;
	}

}
