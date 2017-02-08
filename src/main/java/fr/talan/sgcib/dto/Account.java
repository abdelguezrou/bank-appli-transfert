package fr.talan.sgcib.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * Account dto
 * is serializable {@link Serializable}
 */
public final class Account implements Serializable{

    private String accountId;
    private List<Transaction> transactions;
    private Balance balance;

    /**
     * Account Constructor
     * init accountId & transactions list
     */
    public Account() {
        this.accountId = UUID.randomUUID().toString();
        this.transactions = new ArrayList<>();
        this.balance = new Balance(0D);
    }

    /**
     * Account constructor
     * @param accountId account identifier value
     */
    public Account(final String accountId){
        this.accountId = accountId;
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getAccountId() {
        return accountId;
    }

    public Balance getBalance() {
        return balance;
    }

    /**
     * add transaction to the account
     * @param transaction value to add
     */
    public void addTransaction(final Transaction transaction) {
        switch (transaction.getType().name()){
            case "DEPOSIT" :
                balance.add(transaction.getAmount());
                break;

            case "WITHDRAW":
                balance.subtract(transaction.getAmount());
                break;

            default:
                break;
        }
        transactions.add(transaction);
    }
}
