/**
 * @author Julio (Upaid)
 * 13/05/2010
 */

package net.upaid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.upaid.model.Transaction;

public class TransactionDAOOrcImp implements TransactionDAO {

    public boolean exists(Transaction transaction) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConnectionFactory.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT TELAREACODE, TELNUMBER, NSU_FINANCE, STATUS FROM TB_TRANSACTION ");
            sql.append("  WHERE COMPANYID = 1022");
            sql.append("  AND RECORDDATE >= to_date('13/3/2010 00:00:00','dd/mm/yyyy hh24:mi:ss')");
            sql.append("  AND RECORDDATE <  to_date('13/3/2010 23:59:59','dd/mm/yyyy hh24:mi:ss')");
            sql.append("  AND NSU_FINANCE = ? ");
            sql.append("  AND TELAREACODE = ? ");
            sql.append("  AND TELNUMBER = ?");

            pst = conn.prepareStatement(sql.toString());
            pst.setInt(1, transaction.getNsufinance());
            pst.setInt(2, transaction.getTelareacode());
            pst.setInt(3, transaction.getTelnumber());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String status = rs.getString("STATUS");
                transaction.setStatus(status != null ? status.charAt(0) : 'N');
                return true;
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao executar consulta SQL");
            ex.printStackTrace(System.err);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    System.err.println("Erro ao fechar o Statement");
                }
            }
        }

        return false;

    }
}
