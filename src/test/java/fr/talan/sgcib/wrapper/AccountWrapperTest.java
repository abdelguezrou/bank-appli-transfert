package fr.talan.sgcib.wrapper;

import fr.talan.sgcib.dto.Account;
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
 * AccountWrapperTest
 * test of {@link AccountWrapper}
 */
public class AccountWrapperTest {

    @Test
    public void testRecordTransaction() throws Exception {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);

        accountWrapper.recordTransaction(new Transaction(5D, DEPOSIT, DateTime.now()));

        assertThat(accountWrapper.getHistory().size()).isEqualTo(1);
    }

    @Test
    public void testGetHistory() throws Exception {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);

        accountWrapper.recordTransaction(new Transaction(5D, WITHDRAW, DateTime.now()));

        assertThat(accountWrapper.getHistory().size()).isEqualTo(1);
    }


    @Test
    public void should_calculate_when_multiple_deposite() {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);

        accountWrapper.recordTransaction(new Transaction(5D, DEPOSIT, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(15D, DEPOSIT, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(30D, DEPOSIT, DateTime.now()));

        assertThat(accountWrapper.getHistory().size()).isEqualTo(3);
    }

    @Test
    public void should_calculate_when_one_withdraw() {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);

        accountWrapper.recordTransaction(new Transaction(5D, WITHDRAW, DateTime.now()));

        assertThat(accountWrapper.getHistory().size()).isEqualTo(1);
    }

    @Test
    public void should_calculate_when_withdraws_and_deposits() {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);

        accountWrapper.recordTransaction(new Transaction(15D, DEPOSIT, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(5D, WITHDRAW, DateTime.now()));
        accountWrapper.recordTransaction(new Transaction(2.05D, WITHDRAW, DateTime.now()));

        assertThat(accountWrapper.getHistory().size()).isEqualTo(3);
    }

}