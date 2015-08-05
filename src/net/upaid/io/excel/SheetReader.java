/**
 *
 * @author Julio (Upaid)
 * 13/05/2010
 */

package net.upaid.io.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.upaid.model.Transaction;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SheetReader {

	private Pattern telephonePattern;

	public SheetReader() {
		telephonePattern = Pattern.compile("(\\d\\d)(\\d+)");
	}

	public List<Transaction> readSheet() {

		List<Transaction> transactions = new ArrayList<Transaction>();
		InputStream myxls = null;

		try {
			// Ler arquivo
			// System.out.println("Lendo o arquivo claro.xls");

			// abre o arquivo
			// myxls = new FileInputStream(
			// "UPAID_claro_Mar�o2010.2_versao2003.xls");
			myxls = new FileInputStream("contatos.xls");
					//"UPAID_claro_Março2010.2_versao2003.xls");
			// InputStream myxls = new
			// FileInputStream("UPAID_claro_Mar�o2010.3_versao2003.xls");

			HSSFWorkbook wb = new HSSFWorkbook(myxls);

			// aqui ele irah ler todas as planilhas 09-10-11-12-13-14-15
			// caso queira ler apenas uma de cada vez tirar esse for e buscar
			// por wb wb.getSheetAt(index);

			// abre a primeira planilha do arquivo
			HSSFSheet sheet = wb.getSheetAt(0);
			// pra cada linha da planilha
			for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) { // LINHA

				// System.out.println("Lendo a Linha :" +(j+1));
				// lendo linha a linha
				HSSFRow row = sheet.getRow(j);

				// cria um novo VO para os dados da linha
				Transaction t = readLine(row);
				// adiciona o VO a lista de transacoes
				transactions.add(t);

			}

			System.out.println("Total de linhas lidas na planilha Excel: "
					+ transactions.size());

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problemas ao ler o Arquivo");
			e.printStackTrace();
		} finally {
			try {
				if (myxls != null) {
					myxls.close();
				}
			} catch (IOException ex) {
				System.err.println("Impossivel fechar a planilha");
			}
		}

		return transactions;

	}

	private Transaction readLine(HSSFRow row) {
		Transaction t = new Transaction();
		// Le o codigo da coluna 5 da planilha
		//t.setNsufinance((int) row.getCell(0).getNumericCellValue());
		t.setNsufinance(0);
		// Le o telefone da coluna 1 da planilha, convertendo-o para uma String
//		String tel = String
//				.format("%.0f", row.getCell(1).getNumericCellValue());
		// Divide o telefone em duas cadeias, uma com dois digitos e outra com
		// os digitos restantes
		Matcher m = telephonePattern.matcher("8888");
		// Se o telefone bate com a pattern
		if (m.matches()) {
			// le a area
			t.setTelareacode(Integer.valueOf(m.group(1)));
			// e o telefone
			t.setTelnumber(Integer.valueOf(m.group(2)));
		}
		// Caso contrario o telefone eh invalido
		else {
			System.err.println("Telefone invalido: " + "9999");
		}

		return t;
	}

	public static void main(String[] args) {
		SheetReader sr = new SheetReader();
		List<Transaction> transactions = sr.readSheet();
		int contador = 1;
		for (Transaction transaction : transactions) {
			System.out.print(contador + " " + transaction);
			contador++;
		}
	}

}
