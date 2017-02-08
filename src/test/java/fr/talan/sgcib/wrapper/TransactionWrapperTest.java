package fr.talan.sgcib.wrapper;

import fr.talan.sgcib.dto.Account;
import fr.talan.sgcib.dto.Balance;
import fr.talan.sgcib.dto.Transaction;
import org.joda.time.DateTime;
import org.junit.Test;

import static fr.talan.sgcib.dto.TransactionType.DEPOSIT;
import static fr.talan.sgcib.dto.TransactionType.WITHDRAW;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * TransactionWrapperTest
 * test of {@link TransactionWrapper}
 */
public class TransactionWrapperTest {

    @Test
    public void testGetTransactions() throws Exception {
        final Account account = new Account();
        final TransactionWrapper transactionWrapper = new TransactionWrapper(account.getTransactions());
        assertThat(transactionWrapper.getTransactions()).isEmpty();

        final AccountWrapper accountWrapper = new AccountWrapper(account);
        accountWrapper.recordTransaction(new Transaction(5D, DEPOSIT, DateTime.now()));
        assertThat(transactionWrapper.getTransactions()).isNotEmpty();
    }

    @Test
    public void testCalculateBalance() throws Exception {
        final Account account = new Account();
        final TransactionWrapper transactionWrapper = new TransactionWrapper(account.getTransactions());
        Balance calculate = transactionWrapper.calculateBalance();
        assertThat(calculate.getAmount()).isEqualTo(0D);

        final AccountWrapper accountWrapper = new AccountWrapper(account);
        accountWrapper.recordTransaction(new Transaction(5D, DEPOSIT, DateTime.now()));

        calculate = transactionWrapper.calculateBalance();
        assertThat(calculate.getAmount()).isEqualTo(5D);
    }

    @Test
    public void should_return_empty_balance_when_no_operation() {
        final Account account = new Account();
        final TransactionWrapper transactionWrapper = new TransactionWrapper(account.getTransactions());
        final Balance calculate = transactionWrapper.calculateBalance();
        assertThat(calculate.getAmount()).isEqualTo(0D);
    }

    @Test
    public void should_calculate_when_one_deposite() {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        accountWrapper.recordTransaction(new Transaction(5D, DEPOSIT, DateTime.now()));

        final TransactionWrapper transactionWrapper = new TransactionWrapper(account.getTransactions());
        final Balance calculate = transactionWrapper.calculateBalance();

        assertThat(calculate.getAmount()).isEqualTo(5D);
    }

    @Test
    public void should_calculate_when_multiple_deposite() {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        accountWrapper.recordTransaction(new Transaction(5D, DEPOSIT, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(15D, DEPOSIT, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(30D, DEPOSIT, DateTime.now()));

        final TransactionWrapper transactionWrapper = new TransactionWrapper(account.getTransactions());
        final Balance calculate = transactionWrapper.calculateBalance();

        assertThat(calculate.getAmount()).isEqualTo(50D);
    }

    @Test
    public void should_calculate_when_one_withdraw() {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        accountWrapper.recordTransaction(new Transaction(5D, WITHDRAW, DateTime.now()));

        final TransactionWrapper transactionWrapper = new TransactionWrapper(account.getTransactions());
        final Balance calculate = transactionWrapper.calculateBalance();

        assertThat(calculate.getAmount()).isEqualTo(-5D);
    }

    @Test
    public void should_calculate_when_withdraws_and_deposits() {

        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);

        accountWrapper.recordTransaction(new Transaction(15D, DEPOSIT, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(5D, WITHDRAW, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(2.05D, WITHDRAW, DateTime.now()));

        final TransactionWrapper transactionWrapper = new TransactionWrapper(account.getTransactions());
        final Balance calculate = transactionWrapper.calculateBalance();

        assertThat(calculate.getAmount()).isEqualTo(7.95D);

        assertThat(account.getBalance().getAmount()).isEqualTo(calculate.getAmount());
    }


}