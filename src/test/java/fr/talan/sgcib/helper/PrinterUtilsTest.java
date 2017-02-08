package fr.talan.sgcib.helper;

import fr.talan.sgcib.dto.Account;
import fr.talan.sgcib.dto.Transaction;
import static fr.talan.sgcib.dto.TransactionType.DEPOSIT;
import static fr.talan.sgcib.dto.TransactionType.WITHDRAW;
import fr.talan.sgcib.wrapper.AccountWrapper;
import org.joda.time.DateTime;
import org.junit.*;

import java.text.*;

import static org.junit.Assert.assertEquals;

/**
 * @author  GUEZROU Abdelaziz
 * @since 07/02/2017.
 *
 * PrinterUtilsTest
 * test {@link PrinterUtils}
 */
public class PrinterUtilsTest {

    @Test
    public void testPrintFullStatement() throws Exception {
        final Account account = new Account();
        final DateTime date = new DateTime();
        final AccountWrapper accountWrapper = new AccountWrapper(account);

        accountWrapper.recordTransaction(new Transaction(500.0,DEPOSIT, date));
        String fullStatement = PrinterUtils.printFullStatement(account);
        assertEquals("DATE|AMOUNT|TYPE|BALANCE\n" +
                date.toString()+"|500.0|DEPOSIT|500.0", fullStatement);

        accountWrapper.recordTransaction(new Transaction(100.0, WITHDRAW, date));
        fullStatement = PrinterUtils.printFullStatement(account);
        assertEquals("DATE|AMOUNT|TYPE|BALANCE\n"+
                date.toString()+"|100.0|WITHDRAW|400.0\n" +
                date.toString()+"|500.0|DEPOSIT|400.0", fullStatement);


        accountWrapper.recordTransaction(new Transaction(1000.0,DEPOSIT, date));
        fullStatement = PrinterUtils.printFullStatement(account);
        assertEquals("DATE|AMOUNT|TYPE|BALANCE\n" +
                date.toString()+"|1000.0|DEPOSIT|1400.0\n" +
                date.toString()+"|100.0|WITHDRAW|1400.0\n" +
                date.toString()+"|500.0|DEPOSIT|1400.0", fullStatement);
    }

    @Test
    public void shouldGenerateHeadersOfAnStatement() throws ParseException {
        final Account account = new Account();

        final String fullStatement = PrinterUtils.printFullStatement(account);

        assertEquals("DATE|AMOUNT|TYPE|BALANCE\n", fullStatement);
    }

    @Test
    public void shouldGenerateFullStatementOfAnAccountWithOneTransaction() throws ParseException {
        final Account account = new Account();
        final AccountWrapper accountWrapper = new AccountWrapper(account);
        final DateTime date = new DateTime();
        accountWrapper.recordTransaction(new Transaction(500.0, DEPOSIT, date));

        final String fullStatement = PrinterUtils.printFullStatement(account);

        assertEquals("DATE|AMOUNT|TYPE|BALANCE\n" +
                date.toString()+"|500.0|DEPOSIT|500.0", fullStatement);
    }

}
