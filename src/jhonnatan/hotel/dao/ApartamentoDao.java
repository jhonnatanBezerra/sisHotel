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
import jhonnatan.hotel.jdbc.ConnectionFactory;
import jhonnatan.hotel.model.Apartamento;

/**
 *
 * @author Admin
 */
public class ApartamentoDao implements DaoGenerico<Apartamento>{
    
    private Connection conn;
    private PreparedStatement ps = null;
    
    @Override
    public void salvar(Apartamento ap) throws SQLException {
        String sql = "INSERT INTO apartamento (numeroAP, andar) values(?, ?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, ap.getNumero());
        ps.setString(2, ap.getAndar());
        ps.executeUpdate();
    }

    @Override
    public void atualizar(Apartamento ap) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Apartamento ap) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Apartamento> listar() throws SQLException {
        List<Apartamento> lApartamento = new ArrayList<>();
        HospedagemDao hospgemDao = null;
        String sql = "Select * from apartamento";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            hospgemDao = new HospedagemDao();
            Apartamento ap = new Apartamento();
            ap.setId(rs.getInt("id"));
            ap.setNumero(rs.getString("numeroAP"));
            ap.setAndar(rs.getString("andar"));
        }
        return lApartamento;
    }
    
    public Apartamento buscar(Integer id) throws SQLException{
        String sql = "SELECT * FROM apartamento WHERE id = ?";
        Apartamento ap = null;
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            ap = new Apartamento();
            ap.setId(rs.getInt("id"));
            ap.setNumero(rs.getString("numeroAP"));
            ap.setAndar(rs.getString("andar"));
        }
        return ap;
    }
    
}
