package view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

class ClientUpload extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFileChooser fc;
    JButton b, b1;
    JTextField tf;
    FileInputStream in;
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    int i;

   /** ClientUpload() {
        super("Projeto Leitura de Excel");
        tf = new JTextField();
        tf.setBounds(20, 50, 190, 30);
        add(tf);

        b = new JButton("Upload");
        b.setBounds(250, 50, 80, 30);
        add(b);
        b.addActionListener(this);
//        b1 = new JButton("Leitura");
//        b1.setBounds(250, 100, 80, 30);
//        add(b1);
//        b1.addActionListener(this);
        fc = new JFileChooser();
        setLayout(getLayout());
        setSize(400, 200);
        setVisible(true);
//        try {
//            s = new Socket("localhost", 10);
//            dout = new DataOutputStream(s.getOutputStream());
//            din = new DataInputStream(s.getInputStream());
//            send();
//        } catch (Exception e) {
//        }
    }
    **/

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == b) {
                int x = fc.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    //copy();
                	read(getFile());
                }
            }
            if (e.getSource() == b1) {
                send();
            }
        } catch (Exception ex) {
        }
    }
    
    public File getFile()throws IOException {
    	 File f1 = fc.getSelectedFile();
         tf.setText(f1.getAbsolutePath());
         in = new FileInputStream(f1.getAbsolutePath());
         return f1;
    }

    public void copy() throws IOException {
    	//JOptionPane.showMessageDialog(null, "vai");
        File f1 = fc.getSelectedFile();
        tf.setText(f1.getAbsolutePath());
        in = new FileInputStream(f1.getAbsolutePath());
        System.out.println(f1.getName());
        while ((i = in.read()) != -1) {
            //System.out.print(i);
        }
        //read(f1);
    }

    public void send() throws IOException {
    	/*JOptionPane.showMessageDialog(null, "vem");
        dout.write(i);
        dout.flush();*/
    	//System.out.println("Jesus é meu salvador");

    }
 
    public void read(File file)throws IOException {
    	//JOptionPane.showMessageDialog(null, f.getName());
    	/*JOptionPane.showMessageDialog(null, "vem");
        dout.write(i);
        dout.flush();*/
    	System.out.println("Jesus é meu salvador: "+ file.getName());
    	ExemploXls ex = new ExemploXls();
    	ex.leExcela(file);
        System.exit(0);
    }

    
    public static void main(String[] args) {
    	new ClientUpload();
	}
}
