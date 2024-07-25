package io.twodigits.urlshortener.model;

import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URL, Integer> {
}