package fr.talan.sgcib.helper;

import fr.talan.sgcib.dto.Account;
import fr.talan.sgcib.dto.Transaction;
import fr.talan.sgcib.wrapper.AccountWrapper;
import fr.talan.sgcib.wrapper.TransactionWrapper;

import java.util.*;
import java.util.stream.*;


/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * PrinterUtils
 */
public final class PrinterUtils {

    public static final String SEPARATOR = "|";
    public static final String DATE_HEADER = "DATE";
    public static final String AMOUNT_HEADER = "AMOUNT";
    public static final String TYPE_HEADER = "TYPE";
    public static final String BALANCE_HEADER = "BALANCE";
    public static final String LINE_DELIMITER = "\n";

    /**
     * Constructor
     */
    private PrinterUtils(){
        //Expected : sonar
    }

    /**
     * printFullStatement
     * @param account value
     * @return prepared statement
     */
    public static String printFullStatement(final Account account) {
        return String.join(LINE_DELIMITER,
                printHeaders(),
                printStatementList(account));
    }

    /**
     * printHeaders
     * prepare header
     * @return header
     */
    private static String printHeaders() {
        return String.join(SEPARATOR, DATE_HEADER, AMOUNT_HEADER, TYPE_HEADER, BALANCE_HEADER);
    }

    /**
     * print Transaction
     * @param balance value
     * @param transaction value
     * @return  prepared transaction
     */
    private static String printTransaction(final Double balance, final Transaction transaction) {
        return String.join(SEPARATOR,
                transaction.getDate().toString(),
                String.valueOf(transaction.getAmount()),
                transaction.getType().name(),
                String.valueOf(balance));
    }

    /**
     * print StatementList
     * @param account value
     * @return statement list
     */
    private static String printStatementList(final Account account) {
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        final TransactionWrapper transactionWrapper = new TransactionWrapper(accountWrapper.getHistory());
        final List<String> transactionsLineFormatted = account
                .getTransactions()
                .stream()
                .map(t -> printTransaction(transactionWrapper.calculateBalance().getAmount(), t))
                .collect(Collectors.toList());
        Collections.reverse(transactionsLineFormatted);
        return String.join(LINE_DELIMITER, transactionsLineFormatted);
    }

}
