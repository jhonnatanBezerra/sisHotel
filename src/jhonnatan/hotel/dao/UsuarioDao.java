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
import java.util.ArrayList;
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
    public void atualizar(Usuario user) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getNome());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getSenha());
        ps.setInt(4, user.getId());
        ps.executeUpdate();
        conn.close();
        ps.close();
    }

    @Override
    public void deletar(Usuario user) throws SQLException {
       String sql = "DELETE FROM usuario WHERE id = ?";
       conn = ConnectionFactory.getConnection();
       ps = conn.prepareStatement(sql);
       ps.setInt(1, user.getId());
       ps.execute();
       ps.close();
       conn.close();
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> lUsuario = new ArrayList<>();
        Usuario u = null ;
        String sql = "SELECT * FROM usuario";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setAtivo(rs.getBoolean("ativo"));
            lUsuario.add(u);
        }
        return lUsuario;
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
        rs.close();
        conn.close();
        ps.close();
        return null;
    }
    
    public Usuario buscar (Integer id)throws SQLException{
        Usuario user = null;
        String sql =  "SELECT * FROM usuario WHERE id = ?";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            user = new Usuario();
            user.setId(rs.getInt("id"));
            user.setNome(rs.getString("nome"));
            user.setEmail(rs.getString("email"));
        }
        return user;
    }

}
