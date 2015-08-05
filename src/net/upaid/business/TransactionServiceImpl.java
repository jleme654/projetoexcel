/**
 *
 * @author Julio (Upaid)
 * 13/05/2010
 */

package net.upaid.business;

import net.upaid.dao.TransactionDAO;
import net.upaid.dao.TransactionDAOOrcImp;
import net.upaid.io.excel.SheetReader;
import net.upaid.io.excel.SheetWriter;
import java.util.List;
import net.upaid.model.Transaction;

public class TransactionServiceImpl implements TransactionService {

    TransactionDAO transactionDAO;

    public TransactionServiceImpl() {
        transactionDAO = new TransactionDAOOrcImp();
    }

    public void compareSheetWithDB() {
        List<Transaction> transactions = new SheetReader().readSheet();
        for (Transaction t : transactions) {
            transactionDAO.exists(t);
        }
        new SheetWriter().writeStatus(transactions);
    }
    

}
