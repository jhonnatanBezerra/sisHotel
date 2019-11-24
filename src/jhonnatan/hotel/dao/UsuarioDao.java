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
    public void salvar(Usuario obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    
    public boolean verifica(Usuario user) throws SQLException{
        String sql = "SELECT * FROM usuario";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        if(user.getNome().equals(rs.getString("nome")) && user.getSenha().equals(rs.getString("senha"))){
            return true;
        }
        
        return false;
        
    }
    
}
