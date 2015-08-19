package com.drmtx.app;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WordCountRepository extends PagingAndSortingRepository<WordCount, Long> {

    Page<WordCount> findAllByUrlId(Long urlId, Pageable pageable);

}
