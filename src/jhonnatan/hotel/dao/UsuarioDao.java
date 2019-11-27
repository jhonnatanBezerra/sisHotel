/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import jhonnatan.hotel.jdbc.ConnectionFactory;
import jhonnatan.hotel.model.Usuario;

/**
 *
 * @author Jhonnatan
 */
public class UsuarioDao implements DaoGenerico<Usuario>{

    private Connection conn;
    private PreparedStatement ps = null;
    
    
    @Override
    public void salvar(Usuario user) throws SQLException {
        String sql = "INSERT INTO usuario(nome,email,senha,ativo) VALUES (?,?,?,?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getNome());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getSenha());
        ps.setBoolean(4, true);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Usuario '"+user.getNome()+"' cadastrado");
        
    }

    @Override
    public void atualizar(Usuario obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Usuario user) throws SQLException {
       
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Usuario verifica(Usuario user) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE nome LIKE ? AND senha LIKE ? AND ativo = '1'";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getNome());
        ps.setString(2, user.getSenha());
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            Usuario usuarioNovo = new Usuario();
            usuarioNovo.setId(rs.getInt("id"));
            usuarioNovo.setNome(rs.getString("nome"));
            usuarioNovo.setEmail(rs.getString("email"));
            
            return usuarioNovo;
        }
        return null;
    }
    
}
