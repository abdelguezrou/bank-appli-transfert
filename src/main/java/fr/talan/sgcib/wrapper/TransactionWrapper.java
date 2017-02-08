package fr.talan.sgcib.wrapper;

import fr.talan.sgcib.dto.Account;
import fr.talan.sgcib.dto.Balance;
import fr.talan.sgcib.dto.Transaction;

import java.util.List;

import static fr.talan.sgcib.dto.TransactionType.DEPOSIT;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * TransactionWrapper
 * expose transaction {@link Transaction} operations
 */
public class TransactionWrapper {

    private List<Transaction> transactions;

    public TransactionWrapper(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    /**
     * calculateBalance
     * calculate balance from account transactions
     * @return balance {@link Balance}
     */
    public Balance calculateBalance() {
        final Account account = new Account();
        final Balance balance = account.getBalance();

        if (transactions.isEmpty()) {
            return balance;
        }

        transactions.stream().forEach(operation -> {
            if (operation.getType().equals(DEPOSIT)) {
                balance.add(operation.getAmount());
            } else {
                balance.subtract(operation.getAmount());
            }
        });

        return balance;
    }
}
