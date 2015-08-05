package view.gui;

import java.io.File;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		ClientUpload c = new ClientUpload();
		try {
			c.read(new File("c://contatos.xls"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
