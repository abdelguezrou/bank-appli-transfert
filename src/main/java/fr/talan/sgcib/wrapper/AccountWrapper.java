package fr.talan.sgcib.wrapper;

import fr.talan.sgcib.dto.Account;
import fr.talan.sgcib.dto.Transaction;

import java.util.List;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * AccountWrapper
 * expose account {@link Account} operations
 */
public class AccountWrapper {

    private Account account;

    /**
     * AccountWrapper Constructor
     * @param account value
     */
    public AccountWrapper(final Account account) {
        this.account = account;
    }

    /**
     * recordTransaction
     * add transaction to related account
     * @param transaction dto value
     */
    public void recordTransaction(final Transaction transaction) {
        account.addTransaction(transaction);
    }

    /**
     * getHistory
     * get lit of account transactions
     * @return account transaction list
     */
    public List<Transaction> getHistory() {
        return account.getTransactions();
    }
}
