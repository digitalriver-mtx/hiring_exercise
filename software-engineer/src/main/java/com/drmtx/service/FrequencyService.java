package com.drmtx.service;

import com.drmtx.frequency.Frequency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by antivo on 8/27/15.
 */
@Repository
public interface FrequencyService extends CrudRepository<Frequency, Long> {
    @Override
    Frequency save(Frequency frequency);

    @Override
    Frequency findOne(Long id);
}
