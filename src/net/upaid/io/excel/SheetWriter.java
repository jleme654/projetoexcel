/**
 *
 * @author Julio (Upaid)
 * 13/05/2010
 */

package net.upaid.io.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import net.upaid.model.Transaction;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SheetWriter {

	public void writeStatus(List<Transaction> transactions) {

		InputStream myxlsin = null;
		OutputStream myxlsout = null;

		try {
			// Ler arquivo
			// System.out.println("Lendo o arquivo claro.xls");

			// abre o arquivo
			// myxlsin = new FileInputStream(
			// "UPAID_claro_Mar�o2010.2_versao2003.xls");
			myxlsin = new FileInputStream("contatos3.xls");
					//"UPAID_claro_Março2010.2_versao2003.xls");
			// InputStream myxls = new
			// FileInputStream("UPAID_claro_Mar�o2010.3_versao2003.xls");

			HSSFWorkbook wb = new HSSFWorkbook(myxlsin);

			// aqui ele irah ler todas as planilhas 09-10-11-12-13-14-15
			// caso queira ler apenas uma de cada vez tirar esse for e buscar
			// por wb wb.getSheetAt(index);

			// abre a primeira planilha do arquivo
			HSSFSheet sheet = wb.getSheetAt(4);
			// pra cada linha da planilha
			for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) { // LINHA

				HSSFRow row = sheet.getRow(j);

				Transaction t = transactions.get(j - 1);
				writeCell(row, t);

			}

			myxlsin.close();

			// myxlsout = new FileOutputStream(
			// "UPAID_claro_Mar�o2010.2_versao2003.xls");
			myxlsout = new FileOutputStream("contatos3.xls");
					//"UPAID_claro_Mar�o2010.2_versao2003.xls");

			wb.write(myxlsout);

			System.out.println("Total de linhas escritas na planilha Excel: "
					+ transactions.size());

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problemas ao ler o Arquivo");
			e.printStackTrace();
		} finally {
			try {
				if (myxlsin != null) {
					myxlsin.close();
				}
				if (myxlsout != null) {
					myxlsout.close();
				}
			} catch (IOException ex) {
				System.err.println("Impossível fechar a planilha");
			}
		}

	}

	private void writeCell(HSSFRow row, Transaction t) {
		HSSFCell cell = row.createCell(8, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("" + t.getStatus()));
	}
}
