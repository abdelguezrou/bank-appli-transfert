package fr.talan.sgcib.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_EVEN;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * Balance
 * is serializable {@link Serializable}
 */
public class Balance implements Serializable {

    private Double amount;

    /**
     * constructor
     */
    public Balance() {
        //Expected : sonar
    }

    /**
     * Balance Constructor
     * @param amount value
     *  init amount value
     */
    public Balance(final Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    /**
     * add amount
     * @param amount value to add
     */
    public void add(final Double amount) {
        this.amount += amount;
    }

    /**
     * subtract amount
     * @param amount to subtract
     */
    public void subtract(final Double amount) {
        this.amount -= amount;
    }

    @Override
    public String toString() {
        return "Current balance : " + new BigDecimal(amount).setScale(2, ROUND_HALF_EVEN).toString();
    }
}
