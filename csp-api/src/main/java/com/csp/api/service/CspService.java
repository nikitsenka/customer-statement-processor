package com.csp.api.service;

import com.csp.api.domain.entity.Record;
import com.csp.api.domain.entity.Report;
import com.csp.api.domain.entity.Result;
import com.csp.api.domain.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer Statement Processor service.
 */
@Service
public class CspService {

    @Autowired
    private ReportRepository repository;

    /**
     * Return all reports.
     *
     * @return
     */
    public List<Report> getAll() {
        return repository.findAll();
    }

    /**
     * Process records.
     *
     * @param records
     * @return
     */
    public Report process(List<Record> records) {
        List<Result> results = records.stream()
                .map(record -> new Result(record.getReference(), 1000))
                .collect(Collectors.toList());
        return repository.save(new Report(results));
    }
}
