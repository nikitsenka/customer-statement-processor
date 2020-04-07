package com.csp.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * A record of customer statement.
 */
@Entity
@Table(name = "record")
public class Record {

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
     * IBAN Account number.
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * The starting balance in Euros.
     */
    @Column(name = "start_balance")
    private BigDecimal startBalance;

    /**
     * Either an addition (+) or a deduction (-).
     */
    @Column(name = "mutation")
    private String mutation;

    /**
     * Free text.
     */
    @Column(name = "description")
    private String description;

    /**
     * The end balance in Euros.
     */
    @Column(name = "end_balance")
    private BigDecimal endBalance;

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }
}
