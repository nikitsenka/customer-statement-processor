package com.csp.api.service;

import com.csp.api.domain.entity.Record;
import com.csp.api.domain.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Customer Statement Processor service.
 */
@Service
public class CspService {

    @Autowired
    private RecordRepository repository;

    /**
     * Return all records.
     * @return
     */
    public List<Record> getAll() {
        return repository.findAll();
    }

    /**
     * Process records.
     * @param records
     * @return
     */
    public List<Record> process(Iterable<Record> records) {
        return repository.saveAll(records);
    }
}
