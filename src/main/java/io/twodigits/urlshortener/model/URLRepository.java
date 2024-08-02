package io.twodigits.urlshortener.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URL, Integer> {

	// Method to find all URLs where the 'text' contains the specified part
	List<URL> findByUrlContainingIgnoreCase(String part);
}