package acessoAosDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoDAO {
		

	private Connection getConnection() {
		Connection con = ConnectionFactory.getConnectionMysql();
		return con;
	}
	
	public void CriarAluno(String nome){
        try {
			Connection con = getConnection();
			
			// cria um preparedStatement
	        String sql = "insert into aluno (nome) values (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// preenche os valores
	        stmt.setString(1, nome);
	
	        // executa
	        stmt.execute();
	        
	        stmt.close();		
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void RemoverAluno(int id){
		try {
			Connection con = getConnection();
			
			// cria um preparedStatement
	        String sql = "delete from aluno where id = (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// preenche os valores
	        stmt.setInt(1, id);
	
	        // executa
	        stmt.execute();
	        
	        stmt.close();		
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void AtualizarAluno(Aluno a){
		try {
			Connection con = getConnection();
			
			// cria um preparedStatement
	        String sql = "update aluno set "+
	        		"nome = ?, nota1 = ?, nota2 = ? where id = (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// preenche os valores
			stmt.setString(1, a.getNome());
			stmt.setFloat(2, a.getNota1());
			stmt.setFloat(3, a.getNota2());
	        stmt.setInt(4, a.getId());
	
	        // executa
	        stmt.execute();
	        
	        stmt.close();		
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Aluno getAluno(int id){
		Aluno ret = null;
		try {
			Connection con = getConnection();
			
			// cria um preparedStatement
	        String sql = "select * from aluno where id = (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// preenche os valores
	        stmt.setInt(1, id);
	
	        // executa
	        ResultSet r = stmt.executeQuery();
	        r.next();
	        ret = LerAluno(r);

	        r.close();
	        stmt.close();
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<Aluno> getList(){
		ArrayList<Aluno> ret = new ArrayList<Aluno>();
		try {
			Connection con = getConnection();
			
			// cria um preparedStatement
	        String sql = "select * from aluno";
			PreparedStatement stmt = con.prepareStatement(sql);
				
	        // executa
	        ResultSet r = stmt.executeQuery();
	        while(r.next()){
	        	ret.add(LerAluno(r));
	        }
	        r.close();
	        stmt.close();
	        con.close();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	private Aluno LerAluno(ResultSet r) throws SQLException {
		Aluno a = new Aluno();
		a.setId(r.getInt("id"));
		a.setNome(r.getString("nome"));
		
		float nota1 = r.getFloat("nota1");
		a.setNota1( r.wasNull() ? null : nota1);
		

		float nota2 = r.getFloat("nota2");
		a.setNota2( r.wasNull() ? null : nota2);
		return a;
	}
}

