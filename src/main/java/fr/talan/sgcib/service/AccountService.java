package fr.talan.sgcib.service;

import fr.talan.sgcib.dto.Account;
import fr.talan.sgcib.dto.Transaction;
import fr.talan.sgcib.wrapper.AccountWrapper;
import fr.talan.sgcib.wrapper.TransactionWrapper;
import org.joda.time.DateTime;

import javax.enterprise.inject.Default;
import java.util.List;

import static fr.talan.sgcib.dto.TransactionType.DEPOSIT;
import static fr.talan.sgcib.dto.TransactionType.WITHDRAW;


/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * AccountService
 * implements {@link IAccountService}
 */

@Default
public class AccountService implements IAccountService {

    @Override
    public void deposit(final Account account, final Double amount) {
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        final Transaction transaction = new Transaction(amount, DEPOSIT, DateTime.now());
        accountWrapper.recordTransaction(transaction);
    }

    @Override
    public void withdraw(final Account account, final Double amount) {
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        accountWrapper.recordTransaction(new Transaction(amount, WITHDRAW, DateTime.now()));
    }

    @Override
    public Double getOperationCheck(final Account account) {
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        final List<Transaction> history = accountWrapper.getHistory();
        final TransactionWrapper transactionWrapper = new TransactionWrapper(history);
        return transactionWrapper.calculateBalance().getAmount();
    }

    @Override
    public void transfer(final Account accountFrom, final Account accountTo, final Double amount) {
        final AccountWrapper accountWrapperFrom = new AccountWrapper(accountFrom);
        final AccountWrapper accountWrapperTO = new AccountWrapper(accountTo);
        final DateTime dateTime = DateTime.now();
        accountWrapperFrom.recordTransaction(new Transaction(amount, WITHDRAW, dateTime));
        accountWrapperTO.recordTransaction(new Transaction(amount, DEPOSIT, dateTime));
    }

}