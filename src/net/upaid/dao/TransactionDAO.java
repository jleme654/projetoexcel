/**
 * @author Julio (Upaid)
 * 13/05/2010
 */

package net.upaid.dao;

import net.upaid.model.Transaction;

public interface TransactionDAO {

    boolean exists(Transaction transaction);

}
