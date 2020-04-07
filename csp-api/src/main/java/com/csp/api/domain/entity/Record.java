package com.csp.api.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * A record of customer statement.
 */
public class Record {

    /**
     * Unique identifier of the record.
     */
    private UUID uuid;

    /**
     * Transaction reference.
     */
    private Long reference;

    /**
     * IBAN Account number.
     */
    private String accountNumber;

    /**
     * The starting balance in Euros.
     */
    private BigDecimal startBalance;

    /**
     * Either an addition (+) or a deduction (-).
     */
    private String mutation;

    /**
     * Free text.
     */
    private String description;

    /**
     * The end balance in Euros.
     */
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
