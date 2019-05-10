import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.Professor;
import model.Turma;
import service.ProfessorService;
import service.TurmaService;

public class TurmaTest {

	public int logar(String email, String senha) {
		Connection conn = new ConnectionFactory().getConnection();
		int i = -1;
		String sqlComand = "SELECT id from usuario where email = ? and senha = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setString(1, email);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			
            if(rs.next()) {
				i = rs.getInt("id");	
            }
            
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public static void main (String [] args) {
		
		TurmaTest dao = new TurmaTest();
		String email = "pedro.quessada@usjt.br";
		String senha = "aluno123";
		
		int i = dao.logar(email, senha);
		System.out.print(i);
		
	}
	
}
