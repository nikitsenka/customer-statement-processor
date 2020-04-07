package com.csp.api.controller;

import com.csp.api.domain.entity.Record;
import com.csp.api.domain.entity.Report;
import com.csp.api.service.CspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Main application controller.
 */
@RestController
@RequestMapping("/api")
public class CspRestController {
    @Autowired
    private CspService service;

    /**
     * Return all reports.
     * @return
     */
    @GetMapping("/reports")
    public List<Report> getAll(){
        return service.getAll();
    }

    /**
     * Process records.
     * @param records
     * @return result report
     */
    @PostMapping("/records/process")
    public Report process(@RequestBody List<Record> records){
        return service.process(records);
    }
}
