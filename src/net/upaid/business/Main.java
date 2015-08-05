/**
 * @author Julio (Upaid)
 * 13/05/2010
 * 
*/

package net.upaid.business;


public class Main {

    public static void main(String[] args) {
        TransactionService transactionService = new TransactionServiceImpl();
        transactionService.compareSheetWithDB();
    }

}
