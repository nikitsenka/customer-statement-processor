package com.csp.api.service;

import com.csp.api.domain.entity.Record;
import com.csp.api.domain.entity.Report;
import com.csp.api.domain.entity.Result;
import com.csp.api.domain.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer Statement Processor service.
 */
@Service
public class CspService {

    /**
     * Status code for successfully processed record.
     */
    public static final int SUCCESS = 1000;

    /**
     * Error code for duplicated records.
     */
    public static final int DUPLICATION_ERROR = 3001;

    /**
     * Error code for records with invalid balance.
     */
    public static final int INVALID_END_BALANCE = 3002;

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
                .map(record -> {
                    int resultCode = SUCCESS;
                    if (record.getEndBalance() != null
                            && !record.getEndBalance().equals(record.getStartBalance().add(record.getMutation()))) {
                        resultCode = INVALID_END_BALANCE;
                    }
                    if (Collections.frequency(records, record) > 1) {
                        resultCode = DUPLICATION_ERROR;
                    }
                    return new Result(record.getReference(), resultCode);
                })
                .collect(Collectors.toList());
        return repository.save(new Report(results));
    }
}
