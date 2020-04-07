package com.csp.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * A result report of Customer Statement Processor.
 */
@Entity
@Table(name = "report")
public class Report {
    /**
     * Unique identifier of the Report.
     */
    @Id
    @JsonIgnore
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    /**
     * UTC Time of processing.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    @Column(name = "process_date_time")
    private Instant processTime;

    /**
     * Records processed.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Result> results;

    /**
     * Constructor.
     * @param results
     */
    public Report(List<Result> results) {
        this.processTime = Instant.now();
        this.results = results;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Result> getResults() {
        return results;
    }


}
