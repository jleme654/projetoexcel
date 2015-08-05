package view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CapturarImagem extends JFrame implements ActionListener {

	public CapturarImagem() {

		super("Capturando Imagem");

		JButton botao = new JButton("Abrir Arquivo");
		botao.setFont(new Font("Serif", Font.PLAIN, 26));
		botao.addActionListener(this);

		Container c = getContentPane();
		c.add(BorderLayout.NORTH, botao);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser c = new JFileChooser();
		c.showOpenDialog(this);// abre o arquivo
		File file = c.getSelectedFile();// abre o arquivo selecionado

		try {

			Path path = Paths.get(file.getAbsolutePath());

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this,
					"Não obteve o carregamento do arquivo");
		}
	}

	public static void main(String[] args) {

		new CapturarImagem();
	}

}