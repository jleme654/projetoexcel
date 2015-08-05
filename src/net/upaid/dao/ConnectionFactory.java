/***
 * @author jleme (UPAID)
 * ultima alteração : 26/04/2010
 *  * 
 */

package net.upaid.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static Connection conn;
	private static ConnectionFactory me = new ConnectionFactory();
	private static Properties props = null;
	
	/**
	 * retorna o arquivo de propriedades pedido, (usado apenas para o arquivo do banco de dados
	 * @param arquivo
	 * @return
	 */
	private static Properties getProperties(){
		
		if (props == null){
			props = new Properties();
			InputStream is = null;
			try{
				is = me.getClass().getResourceAsStream("/db.properties");
				props.load(is);
			}catch(IOException ioe){
				ioe.printStackTrace();
			}finally{
				try{
					if (is  != null) is.close();
				}catch(IOException io){
				}
			}
		}
		
		return props;
	}
	
	/**
	 * método local que cria a nova conexão efetivamente
	 * @throws SQLException 
	 * @throws IOException
	 */
	private static void criaNovaConexao() throws SQLException{
		
		Properties props = getProperties();
		if (props != null)
			try {
				Class.forName(props.getProperty("driver"));
				String urlCompleta = props.getProperty("url") + props.getProperty("servidor") + 
					":" + props.getProperty("porta") + ":" + props.getProperty("sid") ; 
				
				conn = DriverManager.getConnection(urlCompleta, props.getProperty("usuario"), props.getProperty("senha"));
				conn.setAutoCommit(false);
//				System.out.println("Mais uma vez: confirmando conexao atraves de criaNovaConexao.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	}
	/**
	 * método chamado de fora para se retornar a conexão unica 
	 * 
	 * esse método é um singleton
	 * @return
	 * @throws Exception
	 */
	public static synchronized Connection getConnection() throws SQLException{
//		logger = Core.getInstance().getLogger();
		try {
			if (conn != null && !conn.isClosed()){
				return conn;
			}else{
//				logger.info("foi criada uma conexão com o banco de dados de homologação");
//				System.out.println("foi criada uma conexão com o banco de dados de PRODUCAO");
				criaNovaConexao();
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			logger.error("erro ao obter a conexão com o banco de dados de homologação", e);
			throw new SQLException("não foi possivel conectar com o banco de dados de PRODUCAO");
		}
		
	}
	
//	// TESTANDO CONEXAO com BD PRODUCAO
//	@SuppressWarnings("static-access")
//	public static void main(String[] args) {
//		ConnectionFactory conn = new ConnectionFactory();
//		try {
//			conn.getConnection();
//			conn.criaNovaConexao();
//			System.out.println("Criando conexao com BD PRODUCAO atraves de getConnection() e em seguida criaNovaConexao()");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
