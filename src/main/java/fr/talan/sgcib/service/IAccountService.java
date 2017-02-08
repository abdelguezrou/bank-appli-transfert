package fr.talan.sgcib.service;

import fr.talan.sgcib.dto.Account;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * Interface AccountService
 */
public interface IAccountService {

    /**
     * deposit
     * @param account value
     * @param amount value
     */
    void deposit(final Account account, final Double amount);

    /**
     * withdraw
     * @param account value
     * @param amount value
     */
    void withdraw(final Account account, final Double amount);

    /**
     * getOperationCheck
     * calculate balance from all account transactions
     * @param account value
     * @return balance
     */
    Double getOperationCheck(final Account account);

    /**
     * transfer
     * @param accountFrom value
     * @param accountTo value
     * @param amount to transfer
     */
    void transfer(final Account accountFrom, final Account accountTo, final Double amount);
}
