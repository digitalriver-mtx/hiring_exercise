package com.drmtx.app.repository;

import com.drmtx.app.domain.WordFrequency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to access JPA persisted word frequencies.
 */
@Repository
public interface WordFrequencyRepository extends CrudRepository<WordFrequency, Long> {

    List<WordFrequency> findByRequestId(String requestId);
}
