package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	
	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        String servidor = "localhost";
        String porta = "3306";
        String database = "mydb";
        String usuario = "root";
        String senha = "";
        
        String connectionURL = "jdbc:mysql://" + servidor + ":" + porta + "/" + database + "?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
        
        try {
            return DriverManager.getConnection(connectionURL, usuario, senha);
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "N�o foi poss�vel conectar com o BD", "Error", JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }
    }
    
	
}
