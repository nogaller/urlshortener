package urlshortener.service;

import java.util.Optional;

import urlshortener.model.URL;

public interface URLShortenerService {
	/**
	 * Get a list of all URLs that belong to a user.<br>
	 * Filter by LongURL containing filter text
	 *
	 * @param user
	 * @param filter
	 * @return a list of users
	 */
	Iterable<URL> listURLs(String user, String filter);

	/**
	 * Add a new URL to the collection of URLs for a user.
	 *
	 * @param user
	 * @param url
	 * @return The URL object which has been created
	 */
	URL addURL(String user, String url);

	/**
	 * Get a specific URL of a user by its ID.
	 *
	 * @param user
	 * @param id
	 * @return The URL object
	 */
	Optional<URL> getURL(String user, Integer id);

	/**
	 * Return a specific URL by ID.
	 *
	 * @param id
	 * @return The URL object
	 */
	Optional<URL> getURL(Integer id);

	/**
	 * Delete a specific URL which belongs to a user.
	 *
	 * @param user
	 * @param id
	 */
	void deleteURL(String user, Integer id);

	/**
	 * Replace stored URL at known ID
	 *
	 * @param user
	 * @param url
	 * @param id
	 */
	void updateURL(String user, String url, Integer id);
}
