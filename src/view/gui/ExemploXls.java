package view.gui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExemploXls {

	public void leExcela(File file) {

		try {
			/**
			 * 
			 * Carrega a planilha
			 */

			Workbook workbook;
			WritableWorkbook workbook2;
			workbook = Workbook.getWorkbook(file);
			workbook2 = Workbook.createWorkbook(new File("c://contatos5.xls"));
			
			/**
			 * 
			 * Aqui é feito o controle de qual aba do xls
			 * 
			 * será realiza a leitura dos dados
			 */

			Sheet sheet = workbook.getSheet(0);
			WritableSheet sheet2 = workbook2.createSheet("Teste Status", 0);
			
			int linhas = sheet.getRows();
			
			try {
				for (int i = 0; i < linhas; i++) {
					WritableCellFormat cf = new WritableCellFormat();
					WritableCellFormat cf2 = new WritableCellFormat();
					WritableCellFormat cf3 = new WritableCellFormat();
					WritableCellFormat cf4 = new WritableCellFormat();
				
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
					String dataFormat = sd.format(new Date());
					
					Label labelx = new Label(4, i, dataFormat, cf);
					Label label = new Label(5, i, "OK", cf2);
					Label label2 = new Label(6, i, "Julio", cf3);
					Label label3 = new Label(7, i, "Leme", cf4);
					
					sheet2.addCell(labelx);
					sheet2.addCell(label);
					sheet2.addCell(label2);
					sheet2.addCell(label3);
				}

				int linhas2 = sheet2.getRows();
				
				for (int i = 0; i < linhas2; i++) {
					Cell celulax = sheet2.getCell(4, i);
					Cell celula21 = sheet2.getCell(5, i);
					Cell celula25 = sheet2.getCell(6, i);
					Cell celula26 = sheet2.getCell(7, i);

					System.out.println(i +"\t"+celulax.getContents() +"\t|"+celula21.getContents()+"\t|"+celula25.getContents()+"\t|"+celula26.getContents());
				}

				workbook2.write();
				workbook2.close();

			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}

			/**
			 * 
			 * Numero de linhas com dados do xls
			 */
	     	for (int i = 0; i < linhas; i++) {
	     		Cell celula1 = sheet.getCell(0, i);
				Cell celula2 = sheet.getCell(1, i);
				Cell celula3 = sheet.getCell(2, i);
				
				System.out.println(" 1: " + celula1.getContents() + "\t\t| 2:" + celula2.getContents() + "\t\t| 3:" + celula3.getContents().replace("N", "S"));

			}
	     	workbook.close();

		} catch (IOException | BiffException e) {
			e.printStackTrace();
		}

	}

}
