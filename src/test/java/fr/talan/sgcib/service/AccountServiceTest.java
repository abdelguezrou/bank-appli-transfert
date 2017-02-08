package fr.talan.sgcib.service;

import fr.talan.sgcib.dto.Account;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * AccountServiceTest
 * test of {@link IAccountService}
 */
public class AccountServiceTest {

    private  IAccountService accountService = new AccountService();

    @Test
    public void testDeposit() throws Exception {
        final Account account = new Account();
        accountService.deposit(account, 20D);
        assertThat(account.getTransactions()).hasSize(1);
    }

    @Test
    public void testWithdraw() throws Exception {
        final Account account = new Account();
        accountService.withdraw(account, 40D);
        assertThat(account.getTransactions()).hasSize(1);
    }

    @Test
    public void testGetOperationCheck() throws Exception {
        final Account account = new Account();
        accountService.deposit(account, 63D);
        final Double balance = accountService.getOperationCheck(account);
        assertThat(balance).isEqualTo(63D);
    }

    @Test
    public void testTransfer() throws Exception {
        final Account accountFrom = new Account();
        final Account accountTo = new Account();

        accountService.deposit(accountFrom, 6500D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(6500D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(0D);

        accountService.transfer(accountFrom, accountTo, 6000D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(500D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(6000D);
    }


    @Test
    public void should_make_multiple_deposit() {
        final Account account = new Account();
        accountService.deposit(account, 20D);
        accountService.deposit(account, 18D);
        assertThat(account.getTransactions()).hasSize(2);
    }

    @Test
    public void should_make_one_withdraw() {
        final Account account = new Account();
        accountService.withdraw(account, 40D);
        assertThat(account.getTransactions()).hasSize(1);
    }

    @Test
    public void should_make_multiple_withdraw() {
        final Account account = new Account();
        accountService.withdraw(account, 66D);
        accountService.withdraw(account, 30D);
        assertThat(account.getTransactions()).hasSize(2);
    }

    @Test
    public void should_print_history_of_one_deposit() {
        final Account account = new Account();
        accountService.deposit(account, 44D);
        final Double balance = accountService.getOperationCheck(account);
        assertThat(balance).isEqualTo(44D);
    }

    @Test
    public void should_print_history_of_one_withdraw() {
        final Account account = new Account();
        accountService.deposit(account, 44D);
        accountService.withdraw(account, 12D);
        final Double balance = accountService.getOperationCheck(account);
        assertThat(balance).isEqualTo(32D);
    }

    @Test
    public void should_make_transfer(){
        final Account accountFrom = new Account();
        final Account accountTo = new Account();

        accountService.deposit(accountFrom, 1000D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(1000D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(0D);

        accountService.transfer(accountFrom, accountTo, 600D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(400D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(600D);
    }

    @Test
    public void should_update_account_balance(){
        final Account accountFrom = new Account();
        final Account accountTo = new Account();

        accountService.deposit(accountFrom, 1000D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(1000D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(0D);

        accountService.transfer(accountFrom, accountTo, 600D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(400D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(600D);
    }

    @Test
    public void should_update_transactions_after_transfer(){
        final Account accountFrom = new Account();
        final Account accountTo = new Account();

        assertThat(accountFrom.getTransactions()).isEmpty();
        assertThat(accountTo.getTransactions()).isEmpty();

        accountService.deposit(accountFrom, 500D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(500D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(0D);

        assertThat(accountFrom.getTransactions().size()).isEqualTo(1);
        assertThat(accountTo.getTransactions()).isEmpty();

        accountService.transfer(accountFrom, accountTo, 300D);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(200D);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(300D);

        assertThat(accountFrom.getTransactions().size()).isEqualTo(2);
        assertThat(accountTo.getTransactions().size()).isEqualTo(1);
    }



}