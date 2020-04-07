package com.csp.api.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A record of customer statement.
 */
public class Record {

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
    private BigDecimal mutation;

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

    public BigDecimal getMutation() {
        return mutation;
    }

    public void setMutation(BigDecimal mutation) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return reference.equals(record.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
