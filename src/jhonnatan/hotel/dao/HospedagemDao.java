/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jhonnatan.hotel.jdbc.ConnectionFactory;
import jhonnatan.hotel.model.Hospedagem;
import jhonnatan.hotel.model.Usuario;
import jhonnatan.hotel.util.Session;


/**
 *
 * @author Admin
 */
public class HospedagemDao {
    
    private Connection conn;
    private PreparedStatement ps = null;
    private Usuario usuarioLogado = Session.getUsuario();
    
    public void salvar(Hospedagem h) throws SQLException{
        
        String sql = "INSERT INTO hospedagem (idHospede, idUsuario, numeroAP, acompanhantes,  status) VALUES (?, ?, ?, ?, ?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, h.getHospede().getID());
        ps.setInt(2, usuarioLogado.getId());
        ps.setString(3, h.getNumeroAP());
        ps.setString(4, h.getQtdAcompanhante());
        ps.setBoolean(5, true);
        
    }
    
}
