package com.drmtx.app.repository;

import com.drmtx.app.domain.WordFrequency;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to access JPA persisted word frequencies.
 */
@Repository
public interface WordFrequencyRepository extends CrudRepository<WordFrequency, Long> {

    List<WordFrequency> findByRequestIdOrderByTermCountDesc(String requestId, Pageable pageable);
}
