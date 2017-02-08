package fr.talan.sgcib.dto;

import org.joda.time.DateTime;
import java.io.Serializable;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 * Transaction dto
 * is serializable {@link Serializable}
 */
public class Transaction implements Serializable {

    private Double amount;
    private TransactionType type;
    private DateTime date;

    /**
     * Constructor
     */
    public Transaction() {
        //expected : sonar
    }

    /**
     * Transaction Constructor
     * @param amount value
     * @param type value
     * @param date value
     */
    public Transaction(final Double amount, final TransactionType type, final DateTime date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    private void setDate(DateTime date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionType getType() {
        return type;
    }

    public DateTime getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "amount : " + amount + "| type : " + type + "| date : " + date;
    }
}
