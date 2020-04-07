package com.csp.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * A result of single record processing.
 */
@Entity
@Table(name = "result")
public class Result {
    /**
     * Unique identifier of the record.
     */
    @Id
    @JsonIgnore
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    /**
     * Transaction reference.
     */
    @Column(name = "reference")
    private Long reference;

    /**
     * Result process code.
     */
    @Column(name = "code")
    private Integer code;

    /**
     * Default constructor.
     */
    public Result() {
    }

    /**
     * Constructor.
     * @param reference
     * @param code
     */
    public Result(Long reference, Integer code) {
        this.reference = reference;
        this.code = code;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getReference() {
        return reference;
    }

    public Integer getCode() {
        return code;
    }
}
