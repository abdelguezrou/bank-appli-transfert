package fr.talan.sgcib.startup;

import fr.talan.sgcib.dto.Account;
import fr.talan.sgcib.dto.Transaction;
import fr.talan.sgcib.service.AccountService;

import java.util.List;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * AppStartup
 * init
 */
public class AppStartup {

    public static void main(String[] args) {
        final Account account = new Account();
        final AccountService accountService = new AccountService();

        accountService.deposit(account, 15.05);
        accountService.withdraw(account, 45.32);
        accountService.deposit(account, 17.36);
        accountService.withdraw(account, 12.8);
        accountService.deposit(account, 200.57);
        accountService.withdraw(account, 120.00);
        accountService.deposit(account, 3467.05);
        accountService.withdraw(account, 36.97);
        accountService.deposit(account, 126.46);
        accountService.withdraw(account, 57.3);
        accountService.deposit(account, 39.3);
        accountService.withdraw(account, 18.4);
        accountService.deposit(account, 46.5);
        accountService.withdraw(account, 90.2);

        final List<Transaction> transactions = account.getTransactions();
        transactions.stream().forEach(transaction -> System.out.println(transaction.toString()));
    }
}
